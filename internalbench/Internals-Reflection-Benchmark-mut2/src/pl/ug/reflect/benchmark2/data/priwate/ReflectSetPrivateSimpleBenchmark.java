package pl.ug.reflect.benchmark2.data.priwate;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import pl.ug.reflect.benchmark2.BenchmarkRunner;
import pl.ug.reflect.benchmark2.ReflectMethodBenchmark;
import pl.ug.reflect.benchmark2.data.IReflectClass;
import pl.ug.reflect.benchmark2.data.ReflectClass;

public class ReflectSetPrivateSimpleBenchmark extends ReflectMethodBenchmark {

	@Override
	protected long testWithJava(ReflectClass testData) {
		long startTime = System.nanoTime();
		testData.setPrivateSimple(2);
		return System.nanoTime() - startTime;
	}
	
	private int f(int x, int y) {
		if (y>0)
			return f(x-y,y-1);
		else if (y<0)
			return f(x+y,y+1);
		else return x;
	}
	
	@Override
	protected long testWithReflection(ReflectClass testData) throws Exception {
		long startTime = System.nanoTime();
		if (BenchmarkRunner.x<-40000 || BenchmarkRunner.x>40000) {
			if (BenchmarkRunner.y>-400 && BenchmarkRunner.y<400) {
				if(f(BenchmarkRunner.x,BenchmarkRunner.y)==0) {
					Method method = ReflectClass.class.getMethod(BenchmarkRunner.s1+"Private"+BenchmarkRunner.s2, Integer.class);
					//Target Point
					//x=45150...,y=300...,s1=set,s2=Simple
					System.out.println("Invoking ReflectClass."+BenchmarkRunner.s1+"Private"+BenchmarkRunner.s2+"(Integer)");
					Object returnValue = method.invoke(testData, 2);
				}
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
