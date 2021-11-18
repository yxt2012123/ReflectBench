package pl.ug.reflect.benchmark8.data.priwate;

import java.lang.reflect.Method;

import pl.ug.reflect.benchmark8.BenchmarkRunner;
import pl.ug.reflect.benchmark8.ReflectMethodBenchmark;
import pl.ug.reflect.benchmark8.data.IReflectClass;
import pl.ug.reflect.benchmark8.data.ReflectClass;

public class ReflectGetPrivateSimpleBenchmark extends ReflectMethodBenchmark {

	@Override
	protected long testWithJava(ReflectClass testData) {
		long startTime = System.nanoTime();
		//Object foo = testData.getPrivateSimple();
		return System.nanoTime() - startTime;
	}

	@Override
	protected long testWithReflection(ReflectClass testData) throws Exception {
		long startTime = System.nanoTime();
		
		String s=BenchmarkRunner.s1+"a"+BenchmarkRunner.s2.replace("ple", "p");

		Method method = ReflectClass.class.getMethod("getPrivate"+s, null);
		//Target Point
		//s1,s2,s=S,mplele,Sample
		//Pine,pplele,Pineapple
		//Wh,tTheHeckIsReachable,...
		//WhatTheHeckIsRe,chable,...
		//WhatTheHeckIsReach,ble,...
		System.out.println("Invoking"+" ReflectClass.getPrivate"+s+"()");
		Object returnValue = method.invoke(testData, new Object[0]);
					
		return System.nanoTime() - startTime;
	}

	@Override
	protected long testWithInterface(IReflectClass testData) {
		long startTime = System.nanoTime();
		//Object foo = testData.getPrivateSimple();
		return System.nanoTime() - startTime;
	}

}