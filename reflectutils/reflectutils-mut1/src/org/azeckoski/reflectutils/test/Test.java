package org.azeckoski.reflectutils.test;

import java.util.HashMap;
import java.util.List;

import org.azeckoski.reflectutils.classes.*;

import org.azeckoski.reflectutils.ReflectUtils;
import org.azeckoski.reflectutils.ClassData;
import org.azeckoski.reflectutils.beanutils.FieldAdapterManager;

public class Test {
	//The original code is collected from the author's github page
	//I am unable to change the package name, so let it be the only benchmark mutant.
	//The reflection includes calling DynaBean/DynaBeanAdapter,
	//calling Finalizer(static),
	//calling constructors of TestEntity/java.util.Vector/ArrayOrderedMap,
	//and finding all members of TestEntity/Object.
	//The reflection points are in ConstructorUtils/FinalizableReferenceQueue/ClassData.
	
	public static void main(String args[]) {
		int a=-1 ;String is1="",is2="",is3="",is4="";
		try {
			a=Integer.parseInt(args[0]);
			is1=args[1];
			is2=args[2];
			is3=args[3];
			is4=args[4];
		}
		catch(Exception e) {System.out.println("5 args needed: int, String, String, String, String");}
		main(a,is1,is2,is3,is4);
		//0,1,2,3,4
		//GetSuperClass
		//UseDynaBean
		//something that includes a '.'
		//anything
	}
	
	public static void main(int a, String s1, String s2, String s3, String s4) {
		//Getting a value from an object field
		if (s1.equals("GetSuperClass"))
			ClassData.DoNotGetSuperClass=false;
		if (s2.equals("UseDynaBean"))
			FieldAdapterManager.DoNotUseDynaBean=false;
		
		if (a==0) {
			TestEntity thing = new TestEntity();
			Object value = ReflectUtils.getInstance().getFieldValue(thing, "entityId");
			System.out.println(value.toString());
			// value will be "33"
			
		}
		
		//Setting a value on an object field
		if (a==1) {
			TestEntity thing1 = new TestEntity();
			ReflectUtils.getInstance().setFieldValue(thing1, "entityId", 3300);
			System.out.println(thing1.getEntityId());
			// value of thing.getEntityId() will be "3300", value is autoconverted into the right type
			
		}
		
		//Setting a nested value on an object field
		if (a==2) {
			Object thing2 = new HashMap(); // using a hashmap for simplicity here, could easily be nested POJOs
			ReflectUtils.getInstance().setFieldValue(thing2, s3, "aaronz");
			//If s3 includes a '.' or other strange things like "[]]",
			//it will reflectively call ArrayOrderedMap's constructor!
			System.out.println(((HashMap)thing2).get(s3));
			
		}
		
		//Constructing classes
		if(a==3) {
			List l = ReflectUtils.getInstance().constructClass(List.class);
			System.out.println(l.size());
			Class<?> clazz = TestEntity.class;
			Object o = ReflectUtils.getInstance().constructClass(clazz);
			System.out.println(((TestEntity)o).getEntityId());
			// o will be an instance of TestEntity
		}
		
		if(a==4) {
			//s4="testNesting.testEntity.entityId";
			//We added this class just because it is interesting
			TestUltraNested un=new TestUltraNested(s4,new TestNesting());
			ReflectUtils.getInstance().setFieldValue(un, "testNesting.testEntity.entityId", s4.length());
			//We dare not modify this. This may cause all possible classes in the
			//ground-truth config file.
			System.out.println(un.getTestNesting().getTestEntity().getEntityId());
		}
		
	}
}
