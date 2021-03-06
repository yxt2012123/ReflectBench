/*
 * Copyright (C) 2007 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.azeckoski.reflectutils.refmap;

import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.azeckoski.reflectutils.LifecycleManager;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URISyntaxException;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * A reference queue with an associated background thread that dequeues
 * references and invokes {@link FinalizableReference#finalizeReferent()} on
 * them.
 *
 * <p>Keep a strong reference to this object until all of the associated
 * referents have been finalized. If this object is garbage collected earlier,
 * the backing thread will not invoke {@code finalizeReferent()} on the
 * remaining references.
 *
 * @author Bob Lee
 */
public class FinalizableReferenceQueue {

  /*
   * The Finalizer thread keeps a phantom reference to this object. When the
   * client (ReferenceMap, for example) no longer has a strong reference to
   * this object, the garbage collector will reclaim it and enqueue the
   * phantom reference. The enqueued reference will trigger the Finalizer to
   * stop.
   *
   * If this library is loaded in the system class loader,
   * FinalizableReferenceQueue can load Finalizer directly with no problems.
   *
   * If this library is loaded in an application class loader, it's important
   * that Finalizer not have a strong reference back to the class loader.
   * Otherwise, you could have a graph like this:
   *
   * Finalizer Thread
   *   runs instance of -> Finalizer.class
   *     loaded by -> Application class loader
   *       which loaded -> ReferenceMap.class
   *         which has a static -> FinalizableReferenceQueue instance
   *
   * Even if no other references to classes from the application class loader
   * remain, the Finalizer thread keeps an indirect strong reference to the
   * queue in ReferenceMap, which keeps the Finalizer running, and as a result,
   * the application class loader can never be reclaimed.
   *
   * This means that dynamically loaded web applications and OSGi bundles can't
   * be unloaded.
   *
   * If the library is loaded in an application class loader, we try to break
   * the cycle by loading Finalizer in its own independent class loader:
   *
   * System class loader
   *   -> Application class loader
   *     -> ReferenceMap
   *     -> FinalizableReferenceQueue
   *     -> etc.
   *   -> Decoupled class loader
   *     -> Finalizer
   *
   * Now, Finalizer no longer keeps an indirect strong reference to the
   * static FinalizableReferenceQueue field in ReferenceMap. The application
   * class loader can be reclaimed at which point the Finalizer thread will
   * stop and its decoupled class loader can also be reclaimed.
   *
   * If any of this fails along the way, we fall back to loading Finalizer
   * directly in the application class loader.
   */

  private static final Logger logger
      = Logger.getLogger(FinalizableReferenceQueue.class.getName());

  private static final String FINALIZER_CLASS_NAME
      = "org.azeckoski.reflectutils.refmap.Finalizer";

  /** Reference to Finalizer.startFinalizer(). */
  private static final Method startFinalizer;
  static {
    Class<?> finalizer;

    // If the LifeCycleManager has been activated, then load the Finalizer in the current ClassLoader
    // so that the Finalizer thread can register to receive shutdown notification
    if (LifecycleManager.isActive()) {
        finalizer = loadFinalizer(new DirectLoader());
    } else {
         finalizer = loadFinalizer(
            new SystemLoader(), new DecoupledLoader(), new DirectLoader());
    }
      
    startFinalizer = getStartFinalizer(finalizer);
  }

  /**
   * The actual reference queue that our background thread will poll.
   */
  final ReferenceQueue<Object> queue;

  /**
   * Constructs a new queue.
   */
  @SuppressWarnings("unchecked")
  public FinalizableReferenceQueue() {
    // We could start the finalizer lazily, but I'd rather it blow up early.
    try {
      this.queue = (ReferenceQueue<Object>) startFinalizer.invoke(null,
          FinalizableReference.class, this, LifecycleManager.isActive());
    } catch (IllegalAccessException e) {
      // Finalizer.startFinalizer() is public.
      throw new AssertionError(e);
    } catch (InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Iterates through the given loaders until it finds one that can load
   * Finalizer.
   *
   * @return Finalizer.class
   */
  private static Class<?> loadFinalizer(FinalizerLoader... loaders) {
    for (FinalizerLoader loader : loaders) {
      Class<?> finalizer = loader.loadFinalizer();
      if (finalizer != null) {
        return finalizer;
      }
    }

    throw new AssertionError();
  }

  /**
   * Loads Finalizer.class.
   */
  interface FinalizerLoader {

    /**
     * Returns Finalizer.class or null if this loader shouldn't or can't load
     * it.
     *
     * @throws SecurityException if we don't have the appropriate priveleges
     */
    Class<?> loadFinalizer();
  }

  /**
   * Tries to load Finalizer from the system class loader. If Finalizer is
   * in the system class path, we needn't create a separate loader.
   */
  static class SystemLoader implements FinalizerLoader {
    public Class<?> loadFinalizer() {
      ClassLoader systemLoader;
      try {
        systemLoader = ClassLoader.getSystemClassLoader();
      } catch (SecurityException e) {
        logger.info("Not allowed to access system class loader.");
        return null;
      }
      if (systemLoader != null) {
        try {
          return systemLoader.loadClass(FINALIZER_CLASS_NAME);
        } catch (ClassNotFoundException e) {
          // Ignore. Finalizer is simply in a child class loader.
          return null;
        }
      } else {
        return null;
      }
    }
  }

  /**
   * Try to load Finalizer in its own class loader. If Finalizer's thread
   * had a direct reference to our class loader (which could be that of
   * a dynamically loaded web application or OSGi bundle), it would prevent
   * our class loader from getting garbage collected.
   */
  static class DecoupledLoader implements FinalizerLoader {

    private static final String LOADING_ERROR = "Could not load Finalizer in"
        + " its own class loader. Loading Finalizer in the current class loader"
        + " instead. As a result, you will not be able to garbage collect this"
        + " class loader. To support reclaiming this class loader, either"
        + " resolve the underlying issue, or move Google Collections to your"
        + " system class path.";

    public Class<?> loadFinalizer() {
      try {
        /*
         * We use URLClassLoader because it's the only concrete class loader
         * implementation in the JDK. If we used our own ClassLoader subclass,
         * Finalizer would indirectly reference this class loader:
         *
         * Finalizer.class ->
         *   CustomClassLoader ->
         *     CustomClassLoader.class ->
         *       This class loader
         *
         * System class loader will (and must) be the parent.
         */
        ClassLoader finalizerLoader = newLoader(getBaseUrl());
        return finalizerLoader.loadClass(FINALIZER_CLASS_NAME);
      } catch (Exception e) {
        logger.log(Level.WARNING, LOADING_ERROR, e);
        return null;
      }
    }

    /**
     * Gets URL for base of path containing Finalizer.class.
     */
    URL getBaseUrl() throws IOException, URISyntaxException {
      // Find URL pointing to Finalizer.class file.
      String finalizerPath = FINALIZER_CLASS_NAME.replace('.', '/') + ".class";
      URL finalizerUrl = getClass().getClassLoader().getResource(finalizerPath);
      if (finalizerUrl == null) {
        throw new FileNotFoundException(finalizerPath);
      }

      // Find URL pointing to base of class path.
      String urlString = finalizerUrl.toString();
      if (!urlString.endsWith(finalizerPath)) {
        throw new IOException("Unsupported path style: " + urlString);
      }
      urlString = urlString.substring(0,
          urlString.length() - finalizerPath.length());
      return new URL(urlString);
    }

    /** Creates a class loader with the given base URL as its classpath. */
    URLClassLoader newLoader(URL base) {
      return new URLClassLoader(new URL[] { base });
    }
  }

  /**
   * Loads Finalizer directly using the current class loader. We won't be
   * able to garbage collect this class loader, but at least the world
   * doesn't end.
   */
  static class DirectLoader implements FinalizerLoader {
    public Class<?> loadFinalizer() {
      try {
        return Class.forName(FINALIZER_CLASS_NAME);
      } catch (ClassNotFoundException e) {
        throw new AssertionError(e);
      }
    }
  }

  /**
   * Looks up Finalizer.startFinalizer().
   */
  static Method getStartFinalizer(Class<?> finalizer) {
	  String start="startFinalizer";
    try {
      return finalizer.getMethod(start, Class.class, Object.class, boolean.class);
      //Target Point
    } catch (NoSuchMethodException e) {
      throw new AssertionError(e);
    }
    //This one is static. We don't want to modify this
    //but just did a little obfuscation.
  }
}

