package com.vgerbot.reflect1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.vgerbot.reflect1.Reflect.BatchReflect;
import com.vgerbot.reflect1.Reflect.ClassReflect;
import com.vgerbot.reflect1.Reflect.ConstructorReflect;
import com.vgerbot.reflect1.Reflect.FieldReflect;
import com.vgerbot.reflect1.Reflect.MethodReflect;
import com.vgerbot.reflect1.Reflect.ObjectReflect;

//The original code is collected from the author's github webpage.
//This benchmark modifies a lot...

public class Test {
    public static void main(String[] args) {
    	
    	int a=0,b=0,c=0,d=0,e=0;
    	//1000,0,0,x,x...
    	//100000,x,20000,x,1000...
    	//200000,x,0,x,24...
    	//100000,-5000001,20000,1000000,1000
    	
    	
    	try {
    		a=Integer.parseInt(args[0]);
    		b=Integer.parseInt(args[1]);
    		c=Integer.parseInt(args[2]);
    		d=Integer.parseInt(args[3]);
    		e=Integer.parseInt(args[4]);

    	}catch (Exception ex) {
    		System.out.println("5 args needed: int, int, int, int, int");
    	}
    	main(a,b,c,d,e);
    	
    }
	
	public static void main(int a,int b,int c,int d,int e) {
		
		if (a>10000000 || a<-10000000) {
			return;
		}
		if (b>10000000 || b<-10000000) {
			return;
		}
		if (c>10000000 || c<-10000000) {
			return;
		}
		if (d>10000000 || d<-10000000) {
			return;
		}
		if (e>10000000 || e<-10000000) {
			return;
		}

		if(a+5*b+3*c==1000) {
			ClassReflect cr = Reflect.on("com.vgerbot.reflect1.Person");
		}
		
		if(a==100000 && c==20000) {
			Reflect.on(Person.class).create();
			Reflect.on(Person.class).create("A."+a);
			if(e==1000) {
				if (b<-1000000) {
					b=-b*2;
				}
				else {
					System.out.println(Reflect.on(Person.class).create("B."+b,a+b+c).method("wasDead").call().off().toString());
				}
			}
			else {
				return;
			}
		}
		
		if(a==200000) {
			Reflect op = Reflect.on(Person.class).create();
			if(b+c+d+e==500000000) {
				System.out.println(op.method("selfIntroduction").callBy(new Person("\"Yakumo Yukari is "+(b+c+d+e)+" years old\"")).off().toString());
				//this is unreachable
			}
			if(a+e+c!=200024) {
				return;
			}
			if (e!=24) {
				return;
			}
			else {
				op.method("sa"+(char)('a'+e),"Hello").call((Object)"Hello, world");
				//e=24,c=0
			}
		}

		//Reflect.on(Person.class).create().method("say",String.class).call((Object)"Hello, world");
		
		if(d*50000==-1539607552 && b>10000000) {
			//a=100000,b=-5000001...,c=20000,d=1000000,e=1000
			List<Person> persons = new ArrayList<Person>();
			for(int i=0;i<2;i++){
				persons.add(new Person("Name"+(i+a)));
			}
			BatchReflect reflect = Reflect.on(persons.toArray());
			for(Reflect r:reflect.call("getName")){
				System.out.println(r.off().toString());
			}
		}
	}
}
