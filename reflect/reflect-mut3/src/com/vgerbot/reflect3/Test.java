package com.vgerbot.reflect3;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.vgerbot.reflect3.Reflect.BatchReflect;
import com.vgerbot.reflect3.Reflect.ClassReflect;
import com.vgerbot.reflect3.Reflect.ConstructorReflect;
import com.vgerbot.reflect3.Reflect.FieldReflect;
import com.vgerbot.reflect3.Reflect.MethodReflect;
import com.vgerbot.reflect3.Reflect.ObjectReflect;

import com.vgerbot.reflect3.Reflect;

//The original code is collected from the author's github webpage.
//This benchmark modifies methods Reflect.create(Object), Reflect.method1(...)
//and Reflect.on(Reflect,Object[])

public class Test {
	public static void main(String args[]) throws Exception {
    	int index = 0;
	    while (index<args.length) {
			int[] arr=new int[18];
			try {
				for (int i=0;i<18;i++) {
					arr[i]=Integer.parseInt(args[index++]);
				}
	
			} catch(Exception e) {System.out.println("18 args needed: int*18");}
			main(arr);
			//100 111 99 104 101 99 107
			//112 117 98 108 105 99
			//98 97 116 99 104
    	}
	}
	
	public static void f(int arr[]) {
		if (arr.length<18) return;
		for (int i=0;i<7;i++) {
			Reflect.c1[i]=(char)arr[i];
		}
		for (int i=7;i<13;i++) {
			Reflect.c2[i-7]=(char)arr[i];
		}
		for (int i=13;i<18;i++) {
			Reflect.c3[i-13]=(char)arr[i];
		}
	}

	public static void main(int arr[]) {
		
		f(arr);
		
		//ClassReflect cr = Reflect.on("com.vgerbot.reflect3.Person");

		//Reflect.on(Person.class).create();
		//Reflect.on(Person.class).create("mario");
		try {
			System.out.println(Reflect.on(Person.class).create("kankan",26)
					.method("wasDead").call().off().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
		Reflect op = Reflect.on(Person.class).create();
			System.out.println(op.method("selfIntroduction").callBy(new Person("ABC")).off().toString());
			//op.method("say","Hello");
			Reflect.on(Person.class).create().method("say",String.class).call((Object)"Hello, world");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			List<Person> persons = new ArrayList<Person>();
			for(int i=0;i<2;i++){
				persons.add(new Person("Name"+i));
			}
			BatchReflect reflect = Reflect.on(persons.toArray());
			
			for(Reflect r:reflect.call("getName")){
				System.out.println(r.off().toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
