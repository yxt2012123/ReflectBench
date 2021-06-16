package pl.ug.reflect.benchmark3.data.priwate;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import pl.ug.reflect.benchmark3.BenchmarkRunner;
import pl.ug.reflect.benchmark3.ReflectMethodBenchmark;
import pl.ug.reflect.benchmark3.data.IReflectClass;
import pl.ug.reflect.benchmark3.data.ReflectClass;

public class ReflectSetPrivateComplexBenchmark extends ReflectMethodBenchmark {

	@Override
	protected long testWithJava(ReflectClass testData) {
		long startTime = System.nanoTime();
		//testData.setPrivateComplex(new ArrayList<Integer>());
		return System.nanoTime() - startTime;
	}
	
	@Override
	protected long testWithReflection(ReflectClass testData) throws Exception {
		long startTime = System.nanoTime();
		if (BenchmarkRunner.s.equals("Complex")) {
			Method method = ReflectClass.class.getMethod("setPrivate"+BenchmarkRunner.s, List.class);
			//Target Point
			//s=Complex
			System.out.println("Invoking ReflectClass.setPrivateComplex(List)");
			Object returnValue = method.invoke(testData, new ArrayList<Integer>());

		}
		return System.nanoTime() - startTime;
	}
	
	@Override
	protected long testWithInterface(IReflectClass testData) {
		long startTime = System.nanoTime();
		//testData.setPrivateComplex(new ArrayList<Integer>());
		return System.nanoTime() - startTime;
	}

}
