package pl.ug.reflect.benchmark3.data.priwate;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import pl.ug.reflect.benchmark3.BenchmarkRunner;
import pl.ug.reflect.benchmark3.ReflectMethodBenchmark;
import pl.ug.reflect.benchmark3.data.IReflectClass;
import pl.ug.reflect.benchmark3.data.ReflectClass;

public class ReflectSetPrivateSimpleBenchmark extends ReflectMethodBenchmark {

	@Override
	protected long testWithJava(ReflectClass testData) {
		long startTime = System.nanoTime();
		//testData.setPrivateSimple(2);
		return System.nanoTime() - startTime;
	}
	
	@Override
	protected long testWithReflection(ReflectClass testData) throws Exception {
		long startTime = System.nanoTime();
		char[] arr=BenchmarkRunner.s.toCharArray();
		if (arr.length==6) {
			if (arr[0]=='S') {
				if (arr[1]=='i') {
					if (arr[2]=='m') {
						if (arr[3]=='p') {
							if (arr[4]=='l') {
								if (arr[5]=='e') {
	
									Method method = ReflectClass.class.getMethod("setPrivate"+BenchmarkRunner.s, Integer.class);
									//Target Point
									//s=Simple
									System.out.println("Invoking"+" ReflectClass.setPrivateSimple(Integer)");
									Object returnValue = method.invoke(testData, 2);
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
		//testData.setPrivateSimple(2);
		return System.nanoTime() - startTime;
	}

}
