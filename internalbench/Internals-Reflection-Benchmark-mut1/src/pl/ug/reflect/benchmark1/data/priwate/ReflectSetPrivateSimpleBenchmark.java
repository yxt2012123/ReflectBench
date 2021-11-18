package pl.ug.reflect.benchmark1.data.priwate;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import pl.ug.reflect.benchmark1.BenchmarkRunner;
import pl.ug.reflect.benchmark1.ReflectMethodBenchmark;
import pl.ug.reflect.benchmark1.data.IReflectClass;
import pl.ug.reflect.benchmark1.data.ReflectClass;

public class ReflectSetPrivateSimpleBenchmark extends ReflectMethodBenchmark {

	@Override
	protected long testWithJava(ReflectClass testData) {
		long startTime = System.nanoTime();
		testData.setPrivateSimple(2);
		return System.nanoTime() - startTime;
	}
	
	@Override
	protected long testWithReflection(ReflectClass testData) throws Exception {
		long startTime = System.nanoTime();
		String simple="Simple";
		if (BenchmarkRunner.x>0) {
			if (BenchmarkRunner.x+1984602932==-973491159) {
				Method method = ReflectClass.class.getMethod("setPrivate"+simple, Integer.class);
				//Target Point
				//Obfuscated
				//x=1336873205
				System.out.println("Invoking ReflectClass.setPrivateSimple(Integer)");
				Object returnValue = method.invoke(testData, 2);
			}
		}
		return System.nanoTime() - startTime;
	}
	
	@Override
	protected long testWithInterface(IReflectClass testData) {
		long startTime = System.nanoTime();
		testData.setPrivateSimple(2);
		return System.nanoTime() - startTime;
	}

}
