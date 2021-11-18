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
package com.genymobile.mirror4.sample;

import com.genymobile.mirror4.Mirror;

public final class Sample {

    private Sample() {
        // don't intantiate
    }
    
    //in this mutant benchmark, we set the branch statements and variables here.

    public static void main(String[] args) {
    	
    	int index = 0;
    	while (index<args.length) {
	    	int a=0,b=0,c=0,d=0,e=0;
	    	double f=0.0,g=0.0,h=0.0;
	    	String s="";
	    	
	    	try {
	    		a=Integer.parseInt(args[index++]);
	    		b=Integer.parseInt(args[index++]);
	    		c=Integer.parseInt(args[index++]);
	    		d=Integer.parseInt(args[index++]);
	    		e=Integer.parseInt(args[index++]);
	    		f=Double.parseDouble(args[index++]);
	    		g=Double.parseDouble(args[index++]);
	    		h=Double.parseDouble(args[index++]);
	    		s=args[index++];
	    	}catch (Exception ex) {
	    		System.out.println("9 args needed: int, int, int, int, int,"
	    				+ " double, double, double, String");
	    	}
	    	main(a,b,c,d,e,f,g,h,s);
    	}
    	
    }
    
    public static void main(int a,int b,int c,int d,int e,double f,double g,double h,String s) {

        System.out.println("Sample Application");

        System.out.println("Create wrapper");
        
        ShyClassWrapper shyClass=null;
        
        try {
	        shyClass = Mirror.create(ShyClassWrapper.class);
        } catch (Exception ex) {ex.printStackTrace();}

        System.out.println("Build ShyClass: ");
        try {
        	if (a==b+e) {
        		if (c+5*d+2*e==15) {
	        		shyClass.buildShyClass(a);
	        		System.out.println("Success");
        		}
        	}
        } catch (Exception ex) {ex.printStackTrace();}

        String value="This one is obviously wrong";

        System.out.println("Print privateField via public method: ");
        try {
        	if (b-c+d*2==5) {
        		if (a==e+4) {
        			if (e==c*2-a) {
		        		value = shyClass.privateFieldToString();
		        		System.out.println(value);
		        		//5,4,3,2,1
        			}
        		}
        	}
        } catch (Exception ex) {ex.printStackTrace();}
        
        System.out.println("Set private field: ");
        try {
        	if (g==256.0) {
        		if (h/g==0.125) {
        			if ((int) (g*h/3-f*Math.PI*Math.E)==0) {
		        		shyClass.setPrivateField(c);
		        		System.out.println(c);
		        		//319.76,256.0,32.0
	        		}
        		}
        	}
        } catch (Exception ex) {ex.printStackTrace();}

        System.out.println("Print privateField via public method: ");
        try {
        	if (b-c+d*2==5) {
        		if (a==e+4) {
        			if (e==c*2-a) {
		        		value = shyClass.privateFieldToString();
		        		System.out.println(value);
        			}
        		}
        	}
        } catch (Exception ex) {ex.printStackTrace();}
        
        System.out.println("Call private method: ");
        try {
        	if (s.startsWith("ac") && s.length()>a && s.charAt(a)=='b') {
        		value = shyClass.sumUp(a);
        		System.out.println(value);
        	}
        } catch (Exception ex) {ex.printStackTrace();}
    }
}