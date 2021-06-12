package com.google.gson1.test;

import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.StringReader;
//import junit.framework.TestCase;

import com.google.gson1.Gson;
import com.google.gson1.JsonElement;
import com.google.gson1.JsonParser;
import com.google.gson1.internal.Streams;
import com.google.gson1.stream.JsonReader;
import com.google.gson1.test.TestTypes.*;

//original: JsonParserTest

public class Test {
	  private JsonParser parser;

	  public void testReadWriteTwoObjects() throws Exception {
	    Gson gson = new Gson();
	    CharArrayWriter writer= new CharArrayWriter();
	    BagOfPrimitives expectedOne = new BagOfPrimitives(1, 1, true, "one");
	    writer.write(gson.toJson(expectedOne).toCharArray());
	    BagOfPrimitives expectedTwo = new BagOfPrimitives(2, 2, false, "two");
	    writer.write(gson.toJson(expectedTwo).toCharArray());
	    CharArrayReader reader = new CharArrayReader(writer.toCharArray());

	    JsonReader parser = new JsonReader(reader);
	    parser.setLenient(true);
	    JsonElement element1 = Streams.parse(parser);
	    JsonElement element2 = Streams.parse(parser);
	    BagOfPrimitives actualOne = gson.fromJson(element1, BagOfPrimitives.class);
	    System.out.println(actualOne.stringValue);
	    BagOfPrimitives actualTwo = gson.fromJson(element2, BagOfPrimitives.class);
	    System.out.println(actualTwo.stringValue);
	  }
	  
	  public void testany() {
		  Gson gson = new Gson();
		  JsonElement element1=parser.parse("{longValue:1;intValue:1;booleanValue:True;stringValue:\"one1\"}");
		  BagOfPrimitives actualOne = gson.fromJson(element1, BagOfPrimitives.class);
		  System.out.println(actualOne.stringValue);
		  //fromJson-deserialize-fromJsonObject-accept-getTarget-constructTarget
		  //-construct-constructWithAllocators-newInstance-findConstructor-getNoArgsConstructor
		  
		  //fromJson-deserialize-fromJsonObject-accept-visitFieldsReflectively-getAllFields
	  }
	  
	  public void testany2() throws Exception {
		  Gson gson = new Gson();
		  CharArrayWriter writer= new CharArrayWriter();
		  BagOfPrimitives expectedOne = new BagOfPrimitives(1, 1, true, "one");
		  System.out.println(gson.toJson(expectedOne).toCharArray());
		  //toJson-toJsonTree-getAdapter-create
		  //toJson-toJsonTree-getAdapter-create-getBoundFields
	  }
	  
	  public static void main(String args[]) {
		  int a=0;
	      try {
	    	  a=Integer.parseInt(args[0]);
	      }catch (Exception e) {
	        System.out.println("1 args needed: int");
	    	//1,2
	      }
	      main(a);
	  }
	  
	  public static void main(int a) {
		  
		  Test t=new Test();
		  t.parser = new JsonParser();
		  try {
			  if (a==1) {
				  t.testany();
			  }
			  else if (a==2) {
				  t.testany2();
			  }
			  else if (a!=1) {
				  return;
			  }
			  else {
				  t.testReadWriteTwoObjects();
				  //impossible, but enough
			  }
		  }
		  catch (Exception e) {
			  e.printStackTrace();
		  }
	  }
}
