package pl.ug.reflect.benchmark6.data.priwate;

import java.lang.reflect.Method;

import pl.ug.reflect.benchmark6.BenchmarkRunner;
import pl.ug.reflect.benchmark6.ReflectMethodBenchmark;
import pl.ug.reflect.benchmark6.data.IReflectClass;
import pl.ug.reflect.benchmark6.data.ReflectClass;

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
		
		String[] arr= {"Simple","Complex","Sample","Rimple","Temple","Apple","Crumble","Rumble",
				"Pineapple","WhatTheHeckIsReachable","Misspelling","All"};
		Class[][] clazz= {null,null,{float.class},{double.class},{long.class},{short.class},
				{boolean.class},{char.class},{byte.class},{String.class},null,null};
		
		if (BenchmarkRunner.test==2) {	
			if (BenchmarkRunner.x>=0 && BenchmarkRunner.x<=11) {
				Method method = ReflectClass.class.getMethod("getPrivate"+arr[BenchmarkRunner.x], clazz[BenchmarkRunner.x]);
				//Target Point
				//x=0,1
				System.out.println("Invoking"+" ReflectClass.getPrivate"+arr[BenchmarkRunner.x]+"(clazz)");
				Object returnValue = method.invoke(testData, new Object[0]);
		
			}
		}
		return System.nanoTime() - startTime;
	}

	@Override
	protected long testWithInterface(IReflectClass testData) {
		long startTime = System.nanoTime();
		//Object foo = testData.getPrivateSimple();
		return System.nanoTime() - startTime;
	}

}