package com.vgerbot.reflect4;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.vgerbot.reflect4.Reflect.BatchReflect;
import com.vgerbot.reflect4.Reflect.ClassReflect;
import com.vgerbot.reflect4.Reflect.ConstructorReflect;
import com.vgerbot.reflect4.Reflect.FieldReflect;
import com.vgerbot.reflect4.Reflect.MethodReflect;
import com.vgerbot.reflect4.Reflect.ObjectReflect;

//The original code is collected from the author's github webpage.
//This benchmark removes the string constants and modify more.

public class Test {
	public static void main(String[] args) throws Exception {
		String is1="",is2="",is3="";
		try {
			is1=args[0];
			is2=args[1];
			is3=args[2];
		}
		catch(Exception e) {System.out.println("3 args needed: String, String, String");}
		main(is1,is2,is3);
	}
	
	public static void main(String s1, String s2, String s3) {

		ClassReflect cr=null;
		try {
			cr = Reflect.on("com.vgerbot.reflect4."+s1);
			if (s1.contains("Reflect") || s1.contains("Test") || s1.contains("FileDriver")) {
				System.out.println("Still able to do something about "+s1+".");
				throw new Exception(s1+" is banned, but is still visited!");
			}
		} catch (Exception e){
			e.printStackTrace();
			return; //Meaningless to try the other invocations.
		}
		//s1=Person
		//s1=Other 18 possibilities including Test, FileDriver, Reflect, ReflectException,
		//Reflect$NULL, Reflect$MethodReflect, Reflect$FieldReflect...

		//Reflect.on(Person.class).create();
		//Reflect.on(Person.class).create("mario");
		try {
			System.out.println(cr.create(s2,26).method(s2).call().off().toString());
		} catch (Exception e){
			e.printStackTrace();
		}
		//s2=wasDead, selfIntroduction, getName
		
		/*
		Reflect op = cr.create();
		System.out.println(op.method("self"+s3).callBy(new Person("My"+s3)).off().toString());
		
		//s3=Introduction
		
		*/
		
		//op.method("say","Hello");

		try {
			cr.create().method(s3,String.class).call((Object)(s3+"Again"));
		} catch (Exception e){
			e.printStackTrace();
		}
		//s3=say
		
		try {
			List<Person> persons = new ArrayList<Person>();
			for(int i=0;i<2;i++){
				persons.add(new Person(s1+i));
			}
			BatchReflect reflect = Reflect.on(persons.toArray());
			for(Reflect r:reflect.call("get"+s1)){
				//This must be invalid
				System.out.println(r.off().toString());
			}
		} catch (Exception e){
			//e.printStackTrace();
			System.out.println("Expected.");
			//ignore.
		}
	}
}
