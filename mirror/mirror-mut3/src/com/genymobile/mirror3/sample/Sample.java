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
package com.genymobile.mirror3.sample;

import com.genymobile.mirror3.Mirror;

public final class Sample {

    private Sample() {
        // don't intantiate
    }

    public static void main(String[] args) {
    	int ia=0;
    	int ib=0;
    	int ic=0;
    	int id=0;
    	int ie=0;
    	double ff=0.0;
    	double ig=0.0;
    	double ih=0.0;
    	String is="";
    	try {
    		ia=Integer.parseInt(args[0]);
    		ib=Integer.parseInt(args[1]);
    		ic=Integer.parseInt(args[2]);
    		id=Integer.parseInt(args[3]);
    		ie=Integer.parseInt(args[4]);
    		ff=Double.parseDouble(args[5]);
    		ig=Double.parseDouble(args[6]);
    		ih=Double.parseDouble(args[7]);
    		is=args[8];
    	}catch (Exception e) {
    		System.out.println("9 args needed: int, int, int, int, int,"
    				+ " double, double, double, String");
    		//1,2,3,4,5,ccccc
    		//1.5707963 0 1.5707963
    		//3.375 4.4816890703 -0.69314718
    	}
    	main(ia,ib,ic,id,ie,ff,ig,ih,is);
    }
    
    public static void main(int ia,int ib,int ic,int id,int ie,double ff,double ig,double ih,String is) {
    	Mirror.a=ia;
    	Mirror.b=ib;
    	Mirror.c=ic;
    	Mirror.d=id;
    	Mirror.e=ie;
    	Mirror.f=ff;
    	Mirror.g=ig;
    	Mirror.h=ih;
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
