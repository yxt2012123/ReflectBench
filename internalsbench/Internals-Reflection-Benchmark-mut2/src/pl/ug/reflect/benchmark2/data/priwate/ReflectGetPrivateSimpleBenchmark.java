package pl.ug.reflect.benchmark2.data.priwate;

import java.lang.reflect.Method;

import pl.ug.reflect.benchmark2.BenchmarkRunner;
import pl.ug.reflect.benchmark2.ReflectMethodBenchmark;
import pl.ug.reflect.benchmark2.data.IReflectClass;
import pl.ug.reflect.benchmark2.data.ReflectClass;

public class ReflectGetPrivateSimpleBenchmark extends ReflectMethodBenchmark {

	@Override
	protected long testWithJava(ReflectClass testData) {
		long startTime = System.nanoTime();
		Object foo = testData.getPrivateSimple();
		return System.nanoTime() - startTime;
	}

	@Override
	protected long testWithReflection(ReflectClass testData) throws Exception {
		long startTime = System.nanoTime();
		if (BenchmarkRunner.y>0) {
			if (BenchmarkRunner.x>BenchmarkRunner.y) {
				if (BenchmarkRunner.x<BenchmarkRunner.s2.length()) {
					if (BenchmarkRunner.s1.length()<=6) {
						Method method = ReflectClass.class.getMethod(BenchmarkRunner.s2+"Private"+BenchmarkRunner.s1, null);
						Object returnValue = method.invoke(testData, new Object[0]);
						//Target Point
						//x=2,y=1,s1=Simple,Sample,Rimple,Temple,Rumble,Apple,s2=get
						System.out.println("Invoking ReflectClass."+BenchmarkRunner.s2+"Private"+BenchmarkRunner.s1+"()");
					}
				}
			}
		}
		return System.nanoTime() - startTime;
	}

	@Override
	protected long testWithInterface(IReflectClass testData) {
		long startTime = System.nanoTime();
		Object foo = testData.getPrivateSimple();
		return System.nanoTime() - startTime;
	}

}