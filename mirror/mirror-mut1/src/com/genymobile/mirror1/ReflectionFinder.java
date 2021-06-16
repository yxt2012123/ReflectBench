/*
 * Copyright (c) 2016 Genymobile
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.genymobile.mirror1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.genymobile.mirror1.annotation.SetInstance;

//import com.genymobile.mirror1.ShyClass;

//8

/**
 * Helper class to find internal fields and methods via reflection.
 */
class ReflectionFinder {
	
	//The reflective invocations are mostly here.
	//In this mutant benchmark, we add some branch statements here.
	//Nobody really write programs like this, but this is a benchmark.

    private ClassLoader classLoader;

    private Unwrapper unwrapper;

    ReflectionFinder(ClassLoader classLoader, Unwrapper unwrapper) {
        this.classLoader = classLoader;
        this.unwrapper = unwrapper;
    }

    public Class<?> findClass(String name) throws ClassNotFoundException {
    	if(Mirror.a==1)
    		return Class.forName(name, true, classLoader);
    	//Target Point 1
    	return null;
    }

    public Field findField(Class<?> clazz, String name) throws NoSuchFieldException {
    	if(Mirror.b==2)
    		return clazz.getDeclaredField(name);
    	//Target Point 2
    	return null;
    }

    public Method findMethod(Class<?> clazz, Method mirrorMethod) throws NoSuchMethodException {
        return findMethod(clazz, mirrorMethod.getName(), retrieveParameters(mirrorMethod));
    }

    public Method findSetInstanceMethod(java.lang.Class clazz) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (isSetInstanceMethods(method)) {
                return method;
            }
        }
        return null;
    }

    private boolean isSetInstanceMethods(Method method) {
        return method.getAnnotation(SetInstance.class) != null;
    }

    private Method findMethod(Class<?> clazz, String name, Class<?>... params) throws NoSuchMethodException {
    	if (Mirror.c!=0.0)
    		return clazz.getDeclaredMethod(name, params);
    	//Target Point 3
    	return null;
    }

    public Constructor<?> findConstructor(Class<?> clazz, Method mirrorMethod) throws NoSuchMethodException {
        return findConstructor(clazz, retrieveParameters(mirrorMethod));
    }

    private Constructor<?> findConstructor(Class<?> clazz, Class<?>... params) throws NoSuchMethodException {
    	if(Mirror.s.length()==3) {
    		Constructor c= clazz.getDeclaredConstructor(params);
    		return c;
    	}
    	//Target Point 4
    	return null;
    }

    private java.lang.Class[] retrieveParameters(Method method) {
        java.lang.Class[] genuineTypes = method.getParameterTypes();
        java.lang.Class[] types = new java.lang.Class[genuineTypes.length];

        for (int i = 0; i < genuineTypes.length; ++i) {
            types[i] = unwrapper.unwrapClass(genuineTypes[i]);
        }
        return types;
    }

}