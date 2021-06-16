package pl.ug.reflect.benchmark7.data.priwate;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import pl.ug.reflect.benchmark7.BenchmarkRunner;
import pl.ug.reflect.benchmark7.ReflectMethodBenchmark;
import pl.ug.reflect.benchmark7.data.IReflectClass;
import pl.ug.reflect.benchmark7.data.ReflectClass;

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
		Method method = ReflectClass.class.getMethod(String.valueOf(BenchmarkRunner.c,0,3)+"Private"+String.valueOf(BenchmarkRunner.c,3,7), List.class);
		//Target (Unreachable) Point
		//c=setComplex
		System.out.println("Invoking ReflectClass."+String.valueOf(BenchmarkRunner.c,0,3)+"Private"+String.valueOf(BenchmarkRunner.c,3,7)+"(List)");
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
