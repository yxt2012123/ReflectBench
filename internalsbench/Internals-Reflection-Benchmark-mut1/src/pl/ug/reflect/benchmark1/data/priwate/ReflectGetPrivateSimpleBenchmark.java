package pl.ug.reflect.benchmark1.data.priwate;

import java.lang.reflect.Method;

import pl.ug.reflect.benchmark1.ReflectMethodBenchmark;
import pl.ug.reflect.benchmark1.data.IReflectClass;
import pl.ug.reflect.benchmark1.data.ReflectClass;
import pl.ug.reflect.benchmark1.BenchmarkRunner;

public class ReflectGetPrivateSimpleBenchmark extends ReflectMethodBenchmark {

	@Override
	protected long testWithJava(ReflectClass testData) {
		long startTime = System.nanoTime();
		Object foo = testData.getPrivateSimple();
		return System.nanoTime() - startTime;
	}

	@Override
	protected long testWithReflection(ReflectClass testData) throws Exception {
		long startTime = System.nanoTime();
		int absx=Math.abs(BenchmarkRunner.x);
		String simple="Simple";
		
		if (absx<100) {
			if (1<<absx<=32) {
				if(absx<<1>8) {
					Method method = ReflectClass.class.getMethod("getPrivate"+simple, null);
					//Target Point
					//Obfuscated
					//x=5/-5
					System.out.println("Invoking ReflectClass.getPrivateSimple()");
					Object returnValue = method.invoke(testData, new Object[0]);
				}
			}
		}
		return System.nanoTime() - startTime;
	}

	@Override
	protected long testWithInterface(IReflectClass testData) {
		long startTime = System.nanoTime();
		Object foo = testData.getPrivateSimple();
		return System.nanoTime() - startTime;
	}

}