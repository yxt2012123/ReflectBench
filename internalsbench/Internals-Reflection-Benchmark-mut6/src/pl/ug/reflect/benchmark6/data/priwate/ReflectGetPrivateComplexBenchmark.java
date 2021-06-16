package pl.ug.reflect.benchmark6.data.priwate;

import java.lang.reflect.Method;

import pl.ug.reflect.benchmark6.BenchmarkRunner;
import pl.ug.reflect.benchmark6.ReflectMethodBenchmark;
import pl.ug.reflect.benchmark6.data.IReflectClass;
import pl.ug.reflect.benchmark6.data.ReflectClass;

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
		
		//This one is not included.
		
		String[] arr= {"Simple","Complex"};
		Class[][] clazz= {null,null};
		
		if (BenchmarkRunner.test==1) {
			if (BenchmarkRunner.x>=0 && BenchmarkRunner.x<=1) {
				Method method = ReflectClass.class.getMethod("getPrivate"+arr[BenchmarkRunner.x], clazz[BenchmarkRunner.x]);
				//Target (Unreachable) Point
				//x=0,1
				System.out.println("Invoking"+" ReflectClass.getPrivateComplex()");
				Object returnValue = method.invoke(testData, new Object[0]);
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