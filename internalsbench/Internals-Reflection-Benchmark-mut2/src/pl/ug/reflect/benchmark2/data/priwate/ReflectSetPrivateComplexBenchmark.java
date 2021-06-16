package pl.ug.reflect.benchmark2.data.priwate;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import pl.ug.reflect.benchmark2.BenchmarkRunner;
import pl.ug.reflect.benchmark2.ReflectMethodBenchmark;
import pl.ug.reflect.benchmark2.data.IReflectClass;
import pl.ug.reflect.benchmark2.data.ReflectClass;

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
		if (BenchmarkRunner.y>200) {
			if (BenchmarkRunner.y<2000) {
				if (Math.abs(BenchmarkRunner.x)<400) {
					if (-700*BenchmarkRunner.y*BenchmarkRunner.x+BenchmarkRunner.x*
							BenchmarkRunner.x*BenchmarkRunner.x+BenchmarkRunner.y*BenchmarkRunner.y<0) {
						Method method = ReflectClass.class.getMethod(BenchmarkRunner.s1+"Private"+BenchmarkRunner.s2, List.class);
						//Target Point
						//x=-390...,y=210...,s1=set,s2=Complex
						System.out.println("Invoking ReflectClass."+BenchmarkRunner.s1+"Private"+BenchmarkRunner.s2+"(List)");
						Object returnValue = method.invoke(testData, new ArrayList<Integer>());
					}
				}
			}
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
