package com.vgerbot.reflect4;

class Person{
	private String name;
	private int age = 0;
	Person(){
		name = "default name";
	}
	Person(String name,int age){
		this.name = name;
		this.age = age;
	}
	Person(String name){
		this.name = name;
	}
	String selfIntroduction(){
		return "Hello!My name is '"+name+"'";
	}
	void say(String something){
		System.out.println(name+" says: "+something);
	}
	boolean wasDead(){
		return age > 200; 
	}
	public String getName(){
		return name;
	}
}
