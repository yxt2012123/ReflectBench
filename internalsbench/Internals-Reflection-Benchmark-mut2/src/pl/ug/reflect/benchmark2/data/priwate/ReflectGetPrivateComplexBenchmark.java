package pl.ug.reflect.benchmark2.data.priwate;

import java.lang.reflect.Method;

import pl.ug.reflect.benchmark2.BenchmarkRunner;
import pl.ug.reflect.benchmark2.ReflectMethodBenchmark;
import pl.ug.reflect.benchmark2.data.IReflectClass;
import pl.ug.reflect.benchmark2.data.ReflectClass;

public class ReflectGetPrivateComplexBenchmark extends ReflectMethodBenchmark {

	@Override
	protected long testWithJava(ReflectClass testData) {
		long startTime = System.nanoTime();
		//Object foo = testData.getPrivateComplex();
		return System.nanoTime() - startTime;
	}

	@Override
	protected long testWithReflection(ReflectClass testData) throws Exception {
		long startTime = System.nanoTime();
		if (BenchmarkRunner.x>=0) {
			if (BenchmarkRunner.y>=100) {
				if (BenchmarkRunner.x*BenchmarkRunner.x*BenchmarkRunner.y<=10000) {
					if (BenchmarkRunner.y>400) return 10l;
					if (Math.sin(BenchmarkRunner.x)>=0) {
						if (Math.cos(BenchmarkRunner.x)>=0.7) {
							Method method = ReflectClass.class.getMethod(BenchmarkRunner.s1+"Private"+BenchmarkRunner.s2+"ble", null);
							//Target Point
							//x=7,y=100...,s1=get,s2=Crum,Rum,WhatTheHeckIsReacha
							System.out.println("Invoking"+" ReflectClass."+BenchmarkRunner.s1+"Private"+BenchmarkRunner.s2+"ble()");
							Object returnValue = method.invoke(testData, new Object[0]);
						}
					}
				}
				else if (BenchmarkRunner.x==10) {
					return 1l;
				}
			}
		}
		
		return System.nanoTime() - startTime;
	}

	@Override
	protected long testWithInterface(IReflectClass testData) {
		long startTime = System.nanoTime();
		//Object foo = testData.getPrivateComplex();
		return System.nanoTime() - startTime;
	}

}