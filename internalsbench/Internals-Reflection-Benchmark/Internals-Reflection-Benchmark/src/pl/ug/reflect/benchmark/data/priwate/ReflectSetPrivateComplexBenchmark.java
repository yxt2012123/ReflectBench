package pl.ug.reflect.benchmark.data.priwate;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import pl.ug.reflect.benchmark.ReflectMethodBenchmark;
import pl.ug.reflect.benchmark.data.IReflectClass;
import pl.ug.reflect.benchmark.data.ReflectClass;

public class ReflectSetPrivateComplexBenchmark extends ReflectMethodBenchmark {

	@Override
	protected long testWithJava(ReflectClass testData) {
		long startTime = System.nanoTime();
		testData.setPrivateComplex(new ArrayList<Integer>());
		return System.nanoTime() - startTime;
	}
	
	@Override
	protected long testWithReflection(ReflectClass testData) throws Exception {
		long startTime = System.nanoTime();
		Method method = ReflectClass.class.getMethod("setPrivateComplex", List.class);
		Object returnValue = method.invoke(testData, new ArrayList<Integer>());
		return System.nanoTime() - startTime;
	}
	
	@Override
	protected long testWithInterface(IReflectClass testData) {
		long startTime = System.nanoTime();
		testData.setPrivateComplex(new ArrayList<Integer>());
		return System.nanoTime() - startTime;
	}

}
