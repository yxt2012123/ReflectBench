package pl.ug.reflect.benchmark5.data.priwate;

import java.lang.reflect.Method;

import pl.ug.reflect.benchmark5.BenchmarkRunner;
import pl.ug.reflect.benchmark5.ReflectMethodBenchmark;
import pl.ug.reflect.benchmark5.data.IReflectClass;
import pl.ug.reflect.benchmark5.data.ReflectClass;

public class ReflectGetPrivateSimpleBenchmark extends ReflectMethodBenchmark {

	@Override
	protected long testWithJava(ReflectClass testData) {
		long startTime = System.nanoTime();
		//Object foo = testData.getPrivateSimple();
		return System.nanoTime() - startTime;
	}
	
	private void sort(int[] arr) {
	    for (int i = 0; i < arr.length - 1; i++) {
	        for (int j = i + 1; j > 0; j--) {
	            if (arr[j] < arr[j - 1]) {
	                int temp = arr[j];
	                arr[j] = arr[j - 1];
	                arr[j - 1] = temp;
	            }
	        }
	    }
	}

	@Override
	protected long testWithReflection(ReflectClass testData) throws Exception {
		long startTime = System.nanoTime();

		int[] arr=BenchmarkRunner.arr.clone();
		String simple="Simple";
		if (arr.length>6 && arr.length<100) {
			sort(arr);
			if (arr[0]==999) {
				if (arr[6]==1001) {
					if (arr[5]==1000) return 10l;

					Method method = ReflectClass.class.getMethod("getPrivate"+simple, null);
					//Target Point
					//Obfuscated
					//arr=[999,999,999,999,999...,1001,...]
					System.out.println("Invoking"+" ReflectClass.getPrivateSimple()");
					Object returnValue = method.invoke(testData, new Object[0]);
				}
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