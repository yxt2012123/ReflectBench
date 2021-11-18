package pl.ug.reflect.benchmark6.data.priwate;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import pl.ug.reflect.benchmark6.BenchmarkRunner;
import pl.ug.reflect.benchmark6.ReflectMethodBenchmark;
import pl.ug.reflect.benchmark6.data.IReflectClass;
import pl.ug.reflect.benchmark6.data.ReflectClass;

public class ReflectSetPrivateComplexBenchmark extends ReflectMethodBenchmark {

	@Override
	protected long testWithJava(ReflectClass testData) {
		long startTime = System.nanoTime();
		//testData.setPrivateComplex(new ArrayList<Integer>());
		return System.nanoTime() - startTime;
	}
	
	@Override
	protected long testWithReflection(ReflectClass testData) throws Exception {
		long startTime = System.nanoTime();
		
		//This one is not included.
		
		if (BenchmarkRunner.test==3) {
		
			String[] arr= {"Simple","Complex","Sample","Rimple","Temple","Apple","Crumble","Rumble",
					"Pineapple","WhatTheHeckIsReachable","Misspelling","All"};
			Class[][] clazz= {{Integer.class},{List.class},{float.class},{double.class},{long.class},{short.class},
					{boolean.class},{char.class},{byte.class},{String.class},null,
					{Integer.class,float.class,double.class,long.class,short.class,
						boolean.class,char.class,byte.class}};
			Object[][] obj= {{2},{new ArrayList<Integer>()},{1.1f},{1.25},{1000l},{(short)255},{false},
					{'"'},{(byte)110},{"All of them"},{},{2,1.1f,1.25,1000l,(short)255,
						false,'"',(byte)110}};
			
			
			int x=BenchmarkRunner.x/12;
			int y=BenchmarkRunner.x%12;
			
			if (x>=0 && x<=11 && y>=0 && y<=11) {
	
				Method method = ReflectClass.class.getMethod("setPrivate"+arr[x], clazz[y]);
				//Target (Unreachable) Point
				//x=0,13,26,39,52,65,78,91,104,117,143
				System.out.println("Invoking ReflectClass.setPrivate"+arr[x]+"(clazz)");
				Object returnValue = method.invoke(testData, obj[y]);
		
			}
		}
		return System.nanoTime() - startTime;
	}
	
	@Override
	protected long testWithInterface(IReflectClass testData) {
		long startTime = System.nanoTime();
		//testData.setPrivateComplex(new ArrayList<Integer>());
		return System.nanoTime() - startTime;
	}

}
