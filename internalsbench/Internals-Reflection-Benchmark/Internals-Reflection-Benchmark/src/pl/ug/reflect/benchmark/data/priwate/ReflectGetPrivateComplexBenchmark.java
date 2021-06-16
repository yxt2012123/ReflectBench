package pl.ug.reflect.benchmark.data.priwate;

import java.lang.reflect.Method;

import pl.ug.reflect.benchmark.ReflectMethodBenchmark;
import pl.ug.reflect.benchmark.data.IReflectClass;
import pl.ug.reflect.benchmark.data.ReflectClass;

public class ReflectGetPrivateComplexBenchmark extends ReflectMethodBenchmark {

	@Override
	protected long testWithJava(ReflectClass testData) {
		long startTime = System.nanoTime();
		Object foo = testData.getPrivateComplex();
		return System.nanoTime() - startTime;
	}

	@Override
	protected long testWithReflection(ReflectClass testData) throws Exception {
		long startTime = System.nanoTime();
		Method method = ReflectClass.class.getMethod("getPrivateComplex", null);
		Object returnValue = method.invoke(testData, new Object[0]);
		return System.nanoTime() - startTime;
	}

	@Override
	protected long testWithInterface(IReflectClass testData) {
		long startTime = System.nanoTime();
		Object foo = testData.getPrivateComplex();
		return System.nanoTime() - startTime;
	}

}