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
package com.genymobile.mirror3;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.genymobile.mirror3.annotation.SetInstance;

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
    		if (Mirror.b==2)
    			if (Mirror.c==3)
    		    	if(Mirror.d==4)
    		    		if (Mirror.e==5)
    		    			return Class.forName(name, true, classLoader);
    	//Target Point 1
    	return null;
    }

    public Field findField(Class<?> clazz, String name) throws NoSuchFieldException {
    	if(Math.sin(Mirror.f)>0.99999)
    		if (Math.cos(Mirror.g)>0.99999)
    			if (Math.tan(Mirror.h)>99999)
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
    	if(Math.cbrt(Mirror.f)<1.50001 && Math.cbrt(Mirror.f)>1.49999)
    		if(Mirror.g>0 && Math.log(Mirror.g)>1.49999 && Math.log(Mirror.g)<1.50001)
    			if (Math.exp(Mirror.h)>0.49999 && Math.exp(Mirror.h)<0.50001)
    				return clazz.getDeclaredMethod(name, params);
    	//Target Point 3
    	return null;
    }

    public Constructor<?> findConstructor(Class<?> clazz, Method mirrorMethod) throws NoSuchMethodException {
        return findConstructor(clazz, retrieveParameters(mirrorMethod));
    }

    private Constructor<?> findConstructor(Class<?> clazz, Class<?>... params) throws NoSuchMethodException {
    	if(Mirror.s.length()==5)
    		if (Mirror.s.charAt(Mirror.a)=='c') 
    			return clazz.getDeclaredConstructor(params);
    		
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