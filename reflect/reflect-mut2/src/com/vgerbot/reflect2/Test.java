package com.vgerbot.reflect2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.vgerbot.reflect2.Reflect.BatchReflect;
import com.vgerbot.reflect2.Reflect.ClassReflect;
import com.vgerbot.reflect2.Reflect.ConstructorReflect;
import com.vgerbot.reflect2.Reflect.FieldReflect;
import com.vgerbot.reflect2.Reflect.MethodReflect;
import com.vgerbot.reflect2.Reflect.ObjectReflect;

//The original code is collected from the author's github webpage.
//This benchmark removes the string constants.

public class Test {
	public static void main(String[] args) throws Exception {
		String is1="",is2="",is3="",is4="",is5="";
		try {
			is1=args[0];
			is2=args[1];
			is3=args[2];
			is4=args[3];
			is5=args[4];
		}
		catch(Exception e) {System.out.println("5 args needed: String, String, String, String, String");}
		main(is1,is2,is3,is4,is5);
	}
	
	public static void main(String s1, String s2, String s3, String s4, String s5) {

		ClassReflect cr=null;
		try {
			cr = Reflect.on("com.vgerbot.reflect2.P"+s1);
		} catch (Exception e){
			e.printStackTrace();
			return; //Meaningless to try the other invocations.
		}
		//s1=erson

		//Reflect.on(Person.class).create();
		//Reflect.on(Person.class).create("mario");
		try {
			System.out.println(cr.create(s2,26000).method("was"+s2).call().off().toString());
		} catch (Exception e){
			e.printStackTrace();
		}
		//s2=Dead
		
		try {
			Reflect op = cr.create();
			System.out.println(op.method("self"+s3).callBy(new Person("My"+s3)).off().toString());
		} catch (Exception e){
			e.printStackTrace();
		}
		//s3=Introduction
		
		//op.method("say","Hello");

		try {
			cr.create().method(s4,String.class).call((Object)s4);
		} catch (Exception e){
			e.printStackTrace();
		}
		//s4=say
		
		try {
			List<Person> persons = new ArrayList<Person>();
			for(int i=0;i<2;i++){
				persons.add(new Person(s5+i));
			}
			BatchReflect reflect = Reflect.on(persons.toArray());
			for(Reflect r:reflect.call("get"+s5)){
				//s5=Name
				System.out.println(r.off().toString());
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
