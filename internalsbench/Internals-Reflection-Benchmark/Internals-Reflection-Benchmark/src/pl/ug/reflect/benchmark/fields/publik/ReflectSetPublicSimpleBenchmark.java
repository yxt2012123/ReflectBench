package pl.ug.reflect.benchmark.fields.publik;

import java.lang.reflect.Field;

import pl.ug.reflect.benchmark.ReflectMethodBenchmark;
import pl.ug.reflect.benchmark.data.IReflectClass;
import pl.ug.reflect.benchmark.data.ReflectClass;

public class ReflectSetPublicSimpleBenchmark extends ReflectMethodBenchmark {

	@Override
	protected long testWithJava(ReflectClass testData) {
		long startTime = System.nanoTime();
		testData.publicSimple = 1;
		return System.nanoTime() - startTime;
	}

	@Override
	protected long testWithReflection(ReflectClass testData) throws Exception {
		long startTime = System.nanoTime();
		Field field = ReflectClass.class.getDeclaredField("publicSimple");
		field.setInt(testData, 1);
		return System.nanoTime() - startTime;
	}

	@Override
	protected long testWithInterface(IReflectClass testData) {
		return -1;
	}

}
