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
package com.genymobile.mirror2.sample;

import com.genymobile.mirror2.Mirror;

public final class Sample {

    private Sample() {
        // don't intantiate
    }
    
    //in this mutant benchmark, we set the branch statements and variables here.

    public static void main(String[] args) {
    	
    	int ia=0,ib=0,ic=0;
    	double id=0.0;
    	
    	try {
    		ia=Integer.parseInt(args[0]);
    		ib=Integer.parseInt(args[1]);
    		ic=Integer.parseInt(args[2]);
    		id=Double.parseDouble(args[3]);
    	}catch (Exception e) {
    		System.out.println("4 args needed: int, int, int, double");
    	}
    	main(ia,ib,ic,id);
    }
    
    public static void main(int a, int b, int c, double d) {

        System.out.println("Sample Application");

        System.out.println("Create wrapper");
        
        ShyClassWrapper shyClass=null;
        
        try {
	        shyClass = Mirror.create(ShyClassWrapper.class);
        } catch (Exception e) {e.printStackTrace();}

        System.out.println("Build ShyClass: ");
        try {
        	if (a==1009007) {
        		shyClass.buildShyClass(a);
        		System.out.println("Success");
        	}
        } catch (Exception e) {e.printStackTrace();}

        String value="This one is obviously wrong";

        System.out.println("Print privateField via public method: ");
        try {
        	if (b==2809991) {
        		value = shyClass.privateFieldToString();
        		System.out.println(value);
        	}
        } catch (Exception e) {e.printStackTrace();}
        
        
        System.out.println("Set private field: ");
        try {
        	if (c==3354355+b) {
        		shyClass.setPrivateField(c);
        		System.out.println(c);
        		//1009007,2809991,6164346
        		//1009007,2809992,6164347
        	}
        } catch (Exception e) {e.printStackTrace();}

        System.out.println("Print privateField via public method: ");
        try {
        	if (b==2809992) {
        		value = shyClass.privateFieldToString();
        		System.out.println(value);
        	}
        } catch (Exception e) {e.printStackTrace();}
        
        System.out.println("Call private method: ");
        try {
        	if (d>1e295) {
        		value = shyClass.sumUp(a);
        		System.out.println(value);
        	}
        } catch (Exception e) {e.printStackTrace();}
    }
}
