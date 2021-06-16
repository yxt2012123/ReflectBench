package pl.ug.reflect.benchmark3.data.priwate;

import java.lang.reflect.Method;

import pl.ug.reflect.benchmark3.BenchmarkRunner;
import pl.ug.reflect.benchmark3.ReflectMethodBenchmark;
import pl.ug.reflect.benchmark3.data.IReflectClass;
import pl.ug.reflect.benchmark3.data.ReflectClass;

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
		if (BenchmarkRunner.s.length()==6) {
			if (BenchmarkRunner.s.substring(2,6).equals("mple")) {

				Method method = ReflectClass.class.getMethod("getPrivate"+BenchmarkRunner.s, null);
				//Target Point
				//s=Simple/Rimple/Temple/Sample
				System.out.println("Invoking"+" ReflectClass.getPrivate"+BenchmarkRunner.s+"()");
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