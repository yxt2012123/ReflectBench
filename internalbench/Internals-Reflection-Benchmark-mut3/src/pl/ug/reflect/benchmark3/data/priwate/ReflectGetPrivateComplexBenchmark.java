package pl.ug.reflect.benchmark3.data.priwate;

import java.lang.reflect.Method;

import pl.ug.reflect.benchmark3.BenchmarkRunner;
import pl.ug.reflect.benchmark3.ReflectMethodBenchmark;
import pl.ug.reflect.benchmark3.data.IReflectClass;
import pl.ug.reflect.benchmark3.data.ReflectClass;

public class ReflectGetPrivateComplexBenchmark extends ReflectMethodBenchmark {

	@Override
	protected long testWithJava(ReflectClass testData) {
		long startTime = System.nanoTime();
		//Object foo = testData.getPrivateComplex();
		return System.nanoTime() - startTime;
	}

	@Override
	protected long testWithReflection(ReflectClass testData) throws Exception {
		long startTime = System.nanoTime();
		if (BenchmarkRunner.s.length()==7) {
			if (BenchmarkRunner.s.charAt(0)=='C') {
				if (BenchmarkRunner.s.charAt(1)=='o') {
					if (BenchmarkRunner.s.charAt(2)=='m') {
						if (BenchmarkRunner.s.charAt(3)=='p') {
							if (BenchmarkRunner.s.charAt(4)=='l') {
								if (BenchmarkRunner.s.charAt(5)=='e') {
									if (BenchmarkRunner.s.charAt(6)=='x') {
										Method method = ReflectClass.class.getMethod("getPrivate"+BenchmarkRunner.s, null);
										//Target Point
										//s=Complex
										System.out.println("Invoking"+" ReflectClass.getPrivateComplex()");
										Object returnValue = method.invoke(testData, new Object[0]);
									}
								}
							}
						}
					}
				}
			}
		}
		
		return System.nanoTime() - startTime;
	}

	@Override
	protected long testWithInterface(IReflectClass testData) {
		long startTime = System.nanoTime();
		//Object foo = testData.getPrivateComplex();
		return System.nanoTime() - startTime;
	}

}