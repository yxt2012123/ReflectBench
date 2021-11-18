package pl.ug.reflect.benchmark7.data.priwate;

import java.lang.reflect.Method;

import pl.ug.reflect.benchmark7.BenchmarkRunner;
import pl.ug.reflect.benchmark7.ReflectMethodBenchmark;
import pl.ug.reflect.benchmark7.data.IReflectClass;
import pl.ug.reflect.benchmark7.data.ReflectClass;

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
		if (BenchmarkRunner.c[2]=='t') {
			BenchmarkRunner.c[3]='R';
		}
		if (BenchmarkRunner.c[3]=='C') {
			BenchmarkRunner.c[2]='t';
		}
		
		Method method = ReflectClass.class.getMethod(String.valueOf(BenchmarkRunner.c,0,3)+"Private"+String.valueOf(BenchmarkRunner.c,3,7), null);
		//Target Point
		//s=ge(!t)Complex,ge(!t)Crumble
		System.out.println("Invoking ReflectClass."+String.valueOf(BenchmarkRunner.c,0,3)+"Private"+String.valueOf(BenchmarkRunner.c,3,7)+"()");
		Object returnValue = method.invoke(testData, new Object[0]);
							
		return System.nanoTime() - startTime;
	}

	@Override
	protected long testWithInterface(IReflectClass testData) {
		long startTime = System.nanoTime();
		//Object foo = testData.getPrivateSimple();
		return System.nanoTime() - startTime;
	}

}