package pl.ug.reflect.benchmark1.data.priwate;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import pl.ug.reflect.benchmark1.BenchmarkRunner;
import pl.ug.reflect.benchmark1.ReflectMethodBenchmark;
import pl.ug.reflect.benchmark1.data.IReflectClass;
import pl.ug.reflect.benchmark1.data.ReflectClass;

public class ReflectSetPrivateComplexBenchmark extends ReflectMethodBenchmark {

	@Override
	protected long testWithJava(ReflectClass testData) {
		long startTime = System.nanoTime();
		testData.setPrivateComplex(new ArrayList<Integer>());
		return System.nanoTime() - startTime;
	}
	
	private int f(int x) {
		int y=0;
		for (int i=0;i<x;i++){
			y=(y+27211)%42349;
		}
		return y;
	}
	
	@Override
	protected long testWithReflection(ReflectClass testData) throws Exception {
		long startTime = System.nanoTime();
		String complex="Complex";
		if (BenchmarkRunner.x>0) {
			if (BenchmarkRunner.x<100000) {
				if (f(BenchmarkRunner.x)==1) {
					Method method = ReflectClass.class.getMethod("setPrivate"+complex, List.class);
					//Target Point
					//Obfuscated
					//x=9285...
					System.out.println("Invoking ReflectClass.setPrivateComplex(List)");
					Object returnValue = method.invoke(testData, new ArrayList<Integer>());
				}
			}
		}
		return System.nanoTime() - startTime;
	}
	
	@Override
	protected long testWithInterface(IReflectClass testData) {
		long startTime = System.nanoTime();
		testData.setPrivateComplex(new ArrayList<Integer>());
		return System.nanoTime() - startTime;
	}

}
