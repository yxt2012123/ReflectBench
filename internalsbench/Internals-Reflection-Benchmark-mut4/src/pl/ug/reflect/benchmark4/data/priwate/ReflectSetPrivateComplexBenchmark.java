package pl.ug.reflect.benchmark4.data.priwate;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import pl.ug.reflect.benchmark4.BenchmarkRunner;
import pl.ug.reflect.benchmark4.ReflectMethodBenchmark;
import pl.ug.reflect.benchmark4.data.IReflectClass;
import pl.ug.reflect.benchmark4.data.ReflectClass;

public class ReflectSetPrivateComplexBenchmark extends ReflectMethodBenchmark {

	@Override
	protected long testWithJava(ReflectClass testData) {
		long startTime = System.nanoTime();
		//testData.setPrivateComplex(new ArrayList<Integer>());
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
		String complex="Complex";

		if (d1<-0.001 || d1>0.001) {
			if (d2>0.001 || d2<-0.001) {
				if (d3>1000.0) {
					if (d2*d2*d1*d1<0.00001) {
						if (d3/d1+d3/d2-d2*d1-1.25*d3*d3-d1*d1-d2*d2>20000.0) {
							if (d1*d1/d2/d2>1.25 || d2*d2/d1/d1>1.25){
								if (d4*d4<d3 && d4/d2>d3) {
									if (d5*(d1*d3*d2*d3*d1+d4*d2-d2*d2*d3)==0.0) {
										Method method = ReflectClass.class.getMethod("setPrivate"+complex, List.class);
										//Target Point
										//Obfuscated
										//d1=0.00113..., d2=0.00101..., d3=1101..., d4=15..., d5=0
										System.out.println("Invoking ReflectClass.setPrivateComplex(List)");
										Object returnValue = method.invoke(testData, new ArrayList<Integer>());
									}
								}
							}
						}
					}
				}
			}
		}
		return System.nanoTime() - startTime;
	}
	
	@Override
	protected long testWithInterface(IReflectClass testData) {
		long startTime = System.nanoTime();
		//testData.setPrivateComplex(new ArrayList<Integer>());
		return System.nanoTime() - startTime;
	}

}
