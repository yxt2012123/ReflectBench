package pl.ug.reflect.benchmark6.fields.publik;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;

import pl.ug.reflect.benchmark6.ReflectMethodBenchmark;
import pl.ug.reflect.benchmark6.data.IReflectClass;
import pl.ug.reflect.benchmark6.data.ReflectClass;

public class ReflectGetPublicSimpleBenchmark extends ReflectMethodBenchmark {

	@Override
	protected long testWithJava(ReflectClass testData) {
		long startTime = System.nanoTime();
		Object foo = testData.publicSimple;
		return System.nanoTime() - startTime;
	}

	@Override
	protected long testWithReflection(ReflectClass testData) throws Exception {
		long startTime = System.nanoTime();
		Field field = ReflectClass.class.getDeclaredField("publicSimple");
		Object returnValue = field.getInt(testData);
		return System.nanoTime() - startTime;
	}

	@Override
	protected long testWithInterface(IReflectClass testData) {
		return -1;
	}

}
