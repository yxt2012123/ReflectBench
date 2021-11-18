package com.google.gson2.test;

import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.StringReader;
//import junit.framework.TestCase;

import com.google.gson2.test.TestTypes.*;
import com.google.gson2.Gson;
import com.google.gson2.JsonElement;
import com.google.gson2.JsonParser;
import com.google.gson2.internal.Streams;
import com.google.gson2.stream.JsonReader;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

//original: ObjectTest
//This benchmark tests different Objects

public class Test {
	  //private JsonParser parser;
	  private Gson gson;
	  
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
  	  
  	  public void testPrimitiveArrayInAnObjectDeserialization() throws Exception {
  		gson=new Gson();
  	    String json = "{\"longArray\":[0,1,2,3,4,5,6,7,8,9]}";
  	    PrimitiveArray target = gson.fromJson(json, PrimitiveArray.class);
  	    System.out.println(target.getExpectedJson());
  	  }
  	  
	  public void testClassWithNoFieldsSerialization() throws Exception {
		  gson=new Gson();
		  System.out.println(gson.toJson(new ClassWithNoFields()));
	  }
	  
	  public void testClassWithTransientFieldsSerialization() throws Exception {
		  gson=new Gson();
		    ClassWithTransientFields<Long> target = new ClassWithTransientFields<Long>(1L);
		    System.out.println(gson.toJson(target));
		  }
	  
	  public void testClassWithTransientFieldsDeserializationTransientFieldsPassedInJsonAreIgnored()
		      throws Exception {
		  gson=new Gson();
		    String json = "{\"transientLongValue\":1,\"longValue\":[1]}";
		    ClassWithTransientFields target = gson.fromJson(json, ClassWithTransientFields.class);
		    System.out.println(target.transientLongValue);
		  }

	  
	  public static void main(String args[]) {
	    	int index = 0;
		    while (index<args.length) {
			  int a=0,b=0,c=0;
		      try {
		    	  a=Integer.parseInt(args[index++]);
		    	  b=Integer.parseInt(args[index++]);
		    	  c=Integer.parseInt(args[index++]);
		      }catch (Exception e) {
		        System.out.println("3 args needed: int, int, int");
		    	//0,1140,10098274
		        //0,10091096,0
		        //8333,0,913684
		        //0,0,41102339
		      }
		      main(a,b,c);
		    }
	  }
	 
	  public static void main(int a,int b,int c) {
		  
		  Test t=new Test();
		  try {
			  if (a+b==1140 && a*b+c==10098274) {
				  t.testArrayOfObjectsSerialization();
				  //toJson-toJsonTree-getAdapter-create
				  //toJson-toJsonTree-getAdapter-create-getBoundFields
			  }
			  if (c<1000 && c>-1000 && a==22*c && 2*b+a*c+a*1000==20182192) {
				  t.testNestedSerialization();
				  //fromJson-deserialize-fromJsonObject-accept-getTarget-constructTarget
				  //-construct-constructWithAllocators-newInstance-findConstructor-getNoArgsConstructor
				  
				  //fromJson-deserialize-fromJsonObject-accept-visitFieldsReflectively-getAllFields
			  }
			  if (a/1040==8 && a+b*2+c*35==31987273) {
				  t.testPrimitiveArrayInAnObjectDeserialization();
			  }
			  if (a<1000 && a>-1000 && a*a*a+b*b+c==41102339) {
				  t.testClassWithNoFieldsSerialization();
			  }
			  if (a<1000 && a>-1000 && c<10 && c>-10 && a*a+c*c*c*c<-1) {
				  t.testClassWithTransientFieldsDeserializationTransientFieldsPassedInJsonAreIgnored();
				  //impossible
			  }
			  if (a<1000 && a>-1000 && a!=0 && b==0 && c==b*15+a && a/(a+b+c)>2) {
				  t.testClassWithTransientFieldsSerialization();
				  //impossible
			  }
		  }
		  catch(Exception e) {
			  e.printStackTrace();
		  }
	  }
}
