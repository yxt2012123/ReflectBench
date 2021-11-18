package pl.ug.reflect.benchmark5.data.priwate;

import java.lang.reflect.Method;

import pl.ug.reflect.benchmark5.BenchmarkRunner;
import pl.ug.reflect.benchmark5.ReflectMethodBenchmark;
import pl.ug.reflect.benchmark5.data.IReflectClass;
import pl.ug.reflect.benchmark5.data.ReflectClass;

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
		
		int[] arr=BenchmarkRunner.arr.clone();
		
		if (arr.length>=7) {
			if (arr[0]=='D') {
				if (arr[1]=='p') {
					if (arr[2]=='n') {
						if (arr[3]=='q') {
							if (arr[4]=='m') {
								if (arr[5]=='f') {
									if (arr[6]=='y') {
										String x="";
										for (int i=0;i<7;i++) {
											x+=(char)(arr[i]-1);
										}
										Method method = ReflectClass.class.getMethod("getPrivate"+x, null);
										//Target Point
										//arr=[68,112,110,113,109,102,121,...]
										System.out.println("Invoking"+" ReflectClass.getPrivateComplex()");
										Object returnValue = method.invoke(testData, new Object[0]);
									}
								}
							}
						}
					}
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