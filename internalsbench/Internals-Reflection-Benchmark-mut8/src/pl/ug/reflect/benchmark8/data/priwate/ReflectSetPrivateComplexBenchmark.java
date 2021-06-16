package pl.ug.reflect.benchmark8.data.priwate;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import pl.ug.reflect.benchmark8.BenchmarkRunner;
import pl.ug.reflect.benchmark8.ReflectMethodBenchmark;
import pl.ug.reflect.benchmark8.data.IReflectClass;
import pl.ug.reflect.benchmark8.data.ReflectClass;

public class ReflectSetPrivateComplexBenchmark extends ReflectMethodBenchmark {
	
	//Not included.

	@Override
	protected long testWithJava(ReflectClass testData) {
		long startTime = System.nanoTime();
		//testData.setPrivateComplex(new ArrayList<Integer>());
		return System.nanoTime() - startTime;
	}
	
	@Override
	protected long testWithReflection(ReflectClass testData) throws Exception {
		long startTime = System.nanoTime();
		Method method = ReflectClass.class.getMethod("setPrivate"+BenchmarkRunner.s1, List.class);
		//Target (Unreachable) Point
		//s=Complex
		System.out.println("Invoking ReflectClass.setPrivateComplex(List)");
		Object returnValue = method.invoke(testData, new ArrayList<Integer>());

		return System.nanoTime() - startTime;
	}
	
	@Override
	protected long testWithInterface(IReflectClass testData) {
		long startTime = System.nanoTime();
		//testData.setPrivateComplex(new ArrayList<Integer>());
		return System.nanoTime() - startTime;
	}

}
