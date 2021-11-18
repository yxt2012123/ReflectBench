package pl.ug.reflect.benchmark4.data.priwate;

import java.lang.reflect.Method;

import pl.ug.reflect.benchmark4.BenchmarkRunner;
import pl.ug.reflect.benchmark4.ReflectMethodBenchmark;
import pl.ug.reflect.benchmark4.data.IReflectClass;
import pl.ug.reflect.benchmark4.data.ReflectClass;

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
		
		double d1=BenchmarkRunner.d1;
		double d2=BenchmarkRunner.d2;
		double d3=BenchmarkRunner.d3;
		double d4=BenchmarkRunner.d4;
		double d5=BenchmarkRunner.d5;
		String simple="Simple";

		if (Double.isFinite(d1)) {
			if (Double.isNaN(d2/d3)){
				if (Double.isInfinite(d3)) {
					if (Double.max(d4, d5)==0.0 && Double.min(d4, d5)==0.0) {
						Method method = ReflectClass.class.getMethod("getPrivate"+simple, null);
						//Target Point
						//Obfuscated
						//d1=0.0, d2=NaN, d3=+-Infinity, d4=+-0.0, d5=+-0.0
						
						System.out.println("Invoking"+" ReflectClass.getPrivateSimple()");
						Object returnValue = method.invoke(testData, new Object[0]);
					}
				}
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