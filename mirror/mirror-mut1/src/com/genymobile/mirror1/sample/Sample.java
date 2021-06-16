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
package com.genymobile.mirror1.sample;

import com.genymobile.mirror1.Mirror;

public final class Sample {

    private Sample() {
        // don't intantiate
    }

    public static void main(String[] args) {
    	int ia=0,ib=0;double ic=0.0; String is="";
    	try {
    		ia=Integer.parseInt(args[0]);
    		ib=Integer.parseInt(args[1]);
    		ic=Double.parseDouble(args[2]);
    		is=args[3];
    	}catch (Exception e) {
    		System.out.println("4 args needed: int, int, double, String");
    		//1,2,3...,abc...
    	}
    	main(ia,ib,ic,is);
    }
    
    public static void main(int ia,int ib,double ic, String is) {
    	Mirror.a=ia;
    	Mirror.b=ib;
    	Mirror.c=ic;
    	Mirror.s=is;

        System.out.println("Sample Application");

        System.out.println("Create wrapper");
        
        ShyClassWrapper shyClass=null;
        
        try {
	        shyClass = Mirror.create(ShyClassWrapper.class);
        } catch (Exception e) {e.printStackTrace();}

        System.out.println("Build ShyClass");
        
        try {
        	shyClass.buildShyClass(18);
        } catch (Exception e) {e.printStackTrace();}

        String value="This one is obviously wrong";

        try {
        	value = shyClass.privateFieldToString();
        } catch (Exception e) {e.printStackTrace();}
        System.out.println("Print privateField via public method: " + value);

        System.out.println("Set private field");
        
        try {
        	shyClass.setPrivateField(10);
        } catch (Exception e) {e.printStackTrace();}

        try {
        	value = shyClass.privateFieldToString();
        } catch (Exception e) {e.printStackTrace();}
        System.out.println("Print privateField via public method: " + value);

        try {
        	value = shyClass.sumUp(32);
        } catch (Exception e) {e.printStackTrace();}
        System.out.println("Call private method: " + value);
    }
}
