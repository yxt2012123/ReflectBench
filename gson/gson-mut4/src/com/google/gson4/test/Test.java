package com.google.gson4.test;

import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.StringReader;
//import junit.framework.TestCase;

import com.google.gson4.Gson;
import com.google.gson4.JsonElement;
import com.google.gson4.JsonParser;
import com.google.gson4.internal.Streams;
import com.google.gson4.stream.JsonReader;
import com.google.gson4.test.TestTypes.BagOfPrimitives;

//original: ObjectTest
//This benchmark filter inputs on the call chain
//In Test, Gson, MiniGson, and ReflectiveTypeAdapter

public class Test {
	  private Gson gson;
	  
	  public void testBagOfPrimitivesSerialization() throws Exception {
		  gson=new Gson();
		    BagOfPrimitives target = new BagOfPrimitives(10, 20, false, "stringValue");
		    System.out.println(gson.toJson(target));
	  }
	  
	  
	  public static void main(String args[]) {
	    	int index = 0;
		    while (index<args.length) {
			  boolean[] arr=new boolean[100];
		      try {
		    	  for (int i=0;i<100;i++) {
		    		  arr[i]=Boolean.parseBoolean(args[index++]);
		    	  }
		      }catch (Exception e) {
		        System.out.println("100 args needed: boolean*100");
		    	//+:0,1,6,7,18,23,31,38,39,47,50,56,70,72,77,78,86,89,91,93,95
		        //-:2,4,9,10,11,12,14,19,27,34,36,40,44,45,49,51,52,71,76,90,97
		        //true true f - f - true true - f
		        //f f f - f - - - true f
		        //- - - true - - - f - -
		        //- true - - f - f - true true
		        //f - - - f f - true f -
		        //true f f - - - true - - -
		        //- - - - - - - - - -
		        //true f true - - - f true true -
		        //- - - - - - true - - true
		        //f true - true - true - f - -
		      }
		      main (arr);
		    }
	  }
	  
	  public static void main(boolean iarr[]) {
		  Gson.arr=iarr;
		  
		  Test t=new Test();
		  try {
			  //The first interception
			  if (iarr[0]&&!iarr[10]&&
			  			!iarr[49]&&!iarr[76]&&
			  			iarr[86]&&iarr[95])
				  t.testBagOfPrimitivesSerialization();
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
	  }
}
