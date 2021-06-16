package pl.ug.reflect.benchmark4.fields.publik;

import java.lang.reflect.Field;
import java.util.List;

import pl.ug.reflect.benchmark4.ReflectMethodBenchmark;
import pl.ug.reflect.benchmark4.data.IReflectClass;
import pl.ug.reflect.benchmark4.data.ReflectClass;

public class ReflectGetPublicComplexBenchmark extends ReflectMethodBenchmark {

	@Override
	protected long testWithJava(ReflectClass testData) {
		long startTime = System.nanoTime();
		Object foo = testData.publicComplex;
		return System.nanoTime() - startTime;
	}

	@Override
	protected long testWithReflection(ReflectClass testData) throws Exception {
		long startTime = System.nanoTime();
		Field field = ReflectClass.class.getDeclaredField("publicComplex");
		List<Integer> returnValue = (List<Integer>) field.get(testData);
		return System.nanoTime() - startTime;
	}

	@Override
	protected long testWithInterface(IReflectClass testData) {
		return -1;
	}

}
