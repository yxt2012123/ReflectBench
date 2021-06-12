package com.vgerbot.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.vgerbot.reflect.Reflect.BatchReflect;
import com.vgerbot.reflect.Reflect.ClassReflect;
import com.vgerbot.reflect.Reflect.ConstructorReflect;
import com.vgerbot.reflect.Reflect.FieldReflect;
import com.vgerbot.reflect.Reflect.MethodReflect;
import com.vgerbot.reflect.Reflect.ObjectReflect;

public class Test {
	public static void main(String args[]) throws Exception {

		ClassReflect cr = Reflect.on("com.vgerbot.reflect.Person");

		Reflect.on(Person.class).create();
		Reflect.on(Person.class).create("mario");
		System.out.println(Reflect.on(Person.class).create("kankan",26).method("wasDead").call().off().toString());
		
		Reflect op = Reflect.on(Person.class).create();

		System.out.println(op.method("selfIntroduction").callBy(new Person("ABC")).off().toString());

		op.method("say","Hello");

		Reflect.on(Person.class).create().method("say",String.class).call((Object)"Hello, world");//output: 你好世界
		
		List<Person> persons = new ArrayList<Person>();
		for(int i=0;i<2;i++){
			persons.add(new Person("Name"+i));
		}
		BatchReflect reflect = Reflect.on(persons.toArray());
		for(Reflect r:reflect.call("getName")){
			System.out.println(r.off().toString());
		}
	}
}
