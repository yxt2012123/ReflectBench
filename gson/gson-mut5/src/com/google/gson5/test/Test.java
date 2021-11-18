package com.google.gson5.test;

import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.StringReader;
//import junit.framework.TestCase;

import com.google.gson5.Gson;
import com.google.gson5.JsonElement;
import com.google.gson5.JsonParser;
import com.google.gson5.internal.Streams;
import com.google.gson5.stream.JsonReader;
import com.google.gson5.test.TestTypes.BagOfPrimitives;

//original: ObjectTest
//This benchmark modifies near the reflective points
//In ReflectiveTypeAdapter and DefaultConstructorAllocator

public class Test {
	  private Gson gson;
	  
	  public void testBagOfPrimitivesDeserialization(String s) throws Exception {
		  gson=new Gson();
		    String json = s;
		    BagOfPrimitives target = gson.fromJson(json, BagOfPrimitives.class);
		    System.out.println(target.getExpectedJson());
		  }
	  
	  
	  public static void main(String args[]) {
	    	int index = 0;
		    while (index<args.length) {
			  String s="";
			  try {s=args[index++];}
			  catch (Exception e) {
				  System.out.println("1 arg needed: String");
			  }
	
		      main (s);
		    }
	  }
	  
	  public static void main(String s) {
		  
		  Test t=new Test();
		  try {
			  if (s.charAt(0)!='{' ||
					  s.charAt(s.length()-1)!='}' 
					  || s.indexOf(':')<=100 || 
					  s.indexOf('"')<=120 ||
					  s.lastIndexOf('"')>=140) {
				  return;
			  }
			  else {
				  t.testBagOfPrimitivesDeserialization(s);
			  }
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
	  }
}
