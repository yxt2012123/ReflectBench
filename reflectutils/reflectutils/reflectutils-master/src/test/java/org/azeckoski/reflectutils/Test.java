package org.azeckoski.reflectutils;

import java.util.HashMap;
import java.util.List;

import org.azeckoski.reflectutils.classes.TestEntity;

public class Test {
	public static void main(String args[]) {
		//Getting a value from an object field
		TestEntity thing = new TestEntity();
		Object value = ReflectUtils.getInstance().getFieldValue(thing, "entityId");
		System.out.println(value.toString());
		// value will be "33"
		//Setting a value on an object field
		TestEntity thing1 = new TestEntity();
		ReflectUtils.getInstance().setFieldValue(thing1, "entityId", 3300);
		System.out.println(thing1.getEntityId());
		// value of thing.getEntityId() will be "3300", value is autoconverted into the right type
		//Setting a nested value on an object field
		Object thing2 = new HashMap(); // using a hashmap for simplicity here, could easily be nested POJOs
		ReflectUtils.getInstance().setFieldValue(thing2, "person.contactInfo.name", "aaronz");
		System.out.println(((HashMap)thing2).get("person.contactInfo.name"));
		// the value of the name field which is on the object in the contactInfo field which is on the object in the person field on the thing object is set to "aaronz"
		//Constructing classes
		List l = ReflectUtils.getInstance().constructClass(List.class);
		System.out.println(l.size());
		Class<?> clazz = TestEntity.class;
		Object o = ReflectUtils.getInstance().constructClass(clazz);
		System.out.println(thing.getEntityId());
		// o will be an instance of TestEntity
	}
}
