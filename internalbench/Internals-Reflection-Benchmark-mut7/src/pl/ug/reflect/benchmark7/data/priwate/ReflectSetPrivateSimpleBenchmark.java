package pl.ug.reflect.benchmark7.data.priwate;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import pl.ug.reflect.benchmark7.BenchmarkRunner;
import pl.ug.reflect.benchmark7.ReflectMethodBenchmark;
import pl.ug.reflect.benchmark7.data.IReflectClass;
import pl.ug.reflect.benchmark7.data.ReflectClass;

public class ReflectSetPrivateSimpleBenchmark extends ReflectMethodBenchmark {
	
	//Not included.

	@Override
	protected long testWithJava(ReflectClass testData) {
		long startTime = System.nanoTime();
		//testData.setPrivateSimple(2);
		return System.nanoTime() - startTime;
	}
	
	@Override
	protected long testWithReflection(ReflectClass testData) throws Exception {
		long startTime = System.nanoTime();
	
		Method method = ReflectClass.class.getMethod("setPrivate"+BenchmarkRunner.c, Integer.class);
		//Target (Unreachable) Point
		//s=Simple
		System.out.println("Invoking"+" ReflectClass.setPrivateSimple(Integer)");
		Object returnValue = method.invoke(testData, 2);

		return System.nanoTime() - startTime;
	}
	
	@Override
	protected long testWithInterface(IReflectClass testData) {
		long startTime = System.nanoTime();
		//testData.setPrivateSimple(2);
		return System.nanoTime() - startTime;
	}

}
