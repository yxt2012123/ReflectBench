package pl.ug.reflect.benchmark4.data.priwate;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import pl.ug.reflect.benchmark4.BenchmarkRunner;
import pl.ug.reflect.benchmark4.ReflectMethodBenchmark;
import pl.ug.reflect.benchmark4.data.IReflectClass;
import pl.ug.reflect.benchmark4.data.ReflectClass;

public class ReflectSetPrivateSimpleBenchmark extends ReflectMethodBenchmark {

	@Override
	protected long testWithJava(ReflectClass testData) {
		long startTime = System.nanoTime();
		//testData.setPrivateSimple(2);
		return System.nanoTime() - startTime;
	}
	
	@Override
	protected long testWithReflection(ReflectClass testData) throws Exception {
		long startTime = System.nanoTime();
		
		double d1=BenchmarkRunner.d1;
		double d2=BenchmarkRunner.d2;
		double d3=BenchmarkRunner.d3;
		double d4=BenchmarkRunner.d4;
		double d5=BenchmarkRunner.d5;
		String simple="Simple";
		
		if (d1>0 && d2>0 && d3>0 && d4>0 && d5>0) {
			if (Math.pow(d1*(1-d5)+d2, d2/d3+d4)<Math.PI*d1+d5-Math.exp(d3+d2+d4*d5-d4/d5)) {
				if (Math.log(d3+d4)+Math.tanh(d1*d2*d2*d5)<d3-1.1*d1-d2-d5/d4) {
					if (Math.sin(d1*d2)+Math.cos(d1+d2+d4+d5)>1) {
						Method method = ReflectClass.class.getMethod("setPrivate"+simple, Integer.class);
						//Target Point
						//Obfuscated
						//d1=6.28..., d2=0.0001..., d3=0.0001..., d4=0.0001..., d5=0.0001...
						System.out.println("Invoking"+" ReflectClass.setPrivateSimple(Integer)");
						Object returnValue = method.invoke(testData, 2);
					}
				}
			}
		}

		return System.nanoTime() - startTime;
	}
	
	@Override
	protected long testWithInterface(IReflectClass testData) {
		long startTime = System.nanoTime();
		//testData.setPrivateSimple(2);
		return System.nanoTime() - startTime;
	}

}
