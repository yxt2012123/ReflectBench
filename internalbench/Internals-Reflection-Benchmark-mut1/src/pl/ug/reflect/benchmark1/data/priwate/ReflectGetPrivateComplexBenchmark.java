package pl.ug.reflect.benchmark1.data.priwate;

import java.lang.reflect.Method;

import pl.ug.reflect.benchmark1.ReflectMethodBenchmark;
import pl.ug.reflect.benchmark1.data.IReflectClass;
import pl.ug.reflect.benchmark1.data.ReflectClass;
import pl.ug.reflect.benchmark1.BenchmarkRunner;

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
		String complex="Complex";
		if (BenchmarkRunner.x>=0) {
			if (BenchmarkRunner.x<=10086) {
				if (BenchmarkRunner.x*BenchmarkRunner.x>=100000000) {
					if (Math.sin(BenchmarkRunner.x)>=0) {
						if (Math.cos(BenchmarkRunner.x)>=0.9) {
							Method method = ReflectClass.class.getMethod("getPrivate"+complex, null);
							//Target Point
							//Obfuscated
							//x=10003...
							System.out.println("Invoking ReflectClass.getPrivateComplex()");
							Object returnValue = method.invoke(testData, new Object[0]);
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
		Object foo = testData.getPrivateComplex();
		return System.nanoTime() - startTime;
	}

}