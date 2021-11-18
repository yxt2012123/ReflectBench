package com.google.gson3.test;

import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.StringReader;
//import junit.framework.TestCase;

import com.google.gson3.Gson;
import com.google.gson3.JsonElement;
import com.google.gson3.JsonParser;
import com.google.gson3.internal.Streams;
import com.google.gson3.stream.JsonReader;
import com.google.gson3.test.TestTypes.*;

//original: ObjectTest
//This benchmark modifies near the reflective points
//In ReflectiveTypeAdapter and DefaultConstructorAllocator

public class Test {
	  private Gson gson;
	  
	  public void testBagOfPrimitivesSerialization() throws Exception {
		  gson=new Gson();
		    BagOfPrimitives target = new BagOfPrimitives(10, 20, false, "stringValue");
		    System.out.println(gson.toJson(target));
	  }
	  
      public void testArrayOfObjectsSerialization() throws Exception {
    	gson=new Gson();
  	    ArrayOfObjects target = new ArrayOfObjects();
  	    System.out.println(gson.toJson(target));
  	  }

      public void testNestedSerialization() throws Exception {
    	  gson=new Gson();
    	    Nested target = new Nested(new BagOfPrimitives(10, 20, false, "stringValue"),
    	       new BagOfPrimitives(30, 40, true, "stringValue"));
    	    System.out.println(gson.toJson(target));
      }
	  
	  public static void main(String args[]) {
	    	int index = 0;
		    while (index<args.length) {
			  int a=0, b=0, c=0;
		      try {
		    	  a=Integer.parseInt(args[index++]);
		    	  b=Integer.parseInt(args[index++]);
		    	  c=Integer.parseInt(args[index++]);
		      }catch (Exception e) {
		        System.out.println("3 args needed: int, int, int");
		    	//11451419,31415926,401355650
		      }
		      main (a,b,c);
		    }
	  }
	  
	  public static void main(int ia,int ib,int ic) {
		  Gson.a=ia;
		  Gson.b=ib;
		  Gson.c=ia+ib+ic;
		  
		  Test t=new Test();
		  try {
			  t.testBagOfPrimitivesSerialization();
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
		  if(Gson.c-Gson.a-Gson.b!=ic) {
			  try {
				  t.testArrayOfObjectsSerialization();
				  //impossible
			  } catch (Exception e) {
				  e.printStackTrace();
			  }
			  try {
				  t.testNestedSerialization();
				  //impossible
			  } catch (Exception e) {
				  e.printStackTrace();
			  }
		  }
	  }
}
