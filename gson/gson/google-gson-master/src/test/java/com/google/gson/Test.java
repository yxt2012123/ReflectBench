package com.google.gson;

import com.google.gson.common.TestTypes.BagOfPrimitives;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.StringReader;
//import junit.framework.TestCase;

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
	  }
	  
	  public void testany2() throws Exception {
		  Gson gson = new Gson();
		  CharArrayWriter writer= new CharArrayWriter();
		  BagOfPrimitives expectedOne = new BagOfPrimitives(1, 1, true, "one");
		  System.out.println(gson.toJson(expectedOne).toCharArray());
	  }
	  
	  public static void main(String args[]) throws Exception {
		  Test t=new Test();
		  t.parser = new JsonParser();

		  //t.testReadWriteTwoObjects();
		  t.testany2();
	  }
}
