package pl.ug.reflect.benchmark5.data.priwate;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import pl.ug.reflect.benchmark5.BenchmarkRunner;
import pl.ug.reflect.benchmark5.ReflectMethodBenchmark;
import pl.ug.reflect.benchmark5.data.IReflectClass;
import pl.ug.reflect.benchmark5.data.ReflectClass;

public class ReflectSetPrivateSimpleBenchmark extends ReflectMethodBenchmark {

	@Override
	protected long testWithJava(ReflectClass testData) {
		long startTime = System.nanoTime();
		//testData.setPrivateSimple(2);
		return System.nanoTime() - startTime;
	}
	
	private int f(int[] arr) {
		return arr[arr[arr[arr[arr[arr[0]]]]]];
	}
	
	@Override
	protected long testWithReflection(ReflectClass testData) throws Exception {
		long startTime = System.nanoTime();
		
		int[] arr=BenchmarkRunner.arr.clone();
		
		for (int i=0;i<arr.length;i++) {
			if (arr[i]>=arr.length) {
				return 0l;
			}
		}
		String simple="Simple";
		
		if (f(arr)>15) {
		
			Method method = ReflectClass.class.getMethod("setPrivate"+simple, Integer.class);
			//Target Point
			//Obfuscated
			//arr=[16,16,16,16,......,16...]
			System.out.println("Invoking"+" ReflectClass.setPrivateSimple(Integer)");
			Object returnValue = method.invoke(testData, 2);
		
		}


		return System.nanoTime() - startTime;
	}
	
	@Override
	protected long testWithInterface(IReflectClass testData) {
		long startTime = System.nanoTime();
		//testData.setPrivateSimple(2);
		return System.nanoTime() - startTime;
	}

}
