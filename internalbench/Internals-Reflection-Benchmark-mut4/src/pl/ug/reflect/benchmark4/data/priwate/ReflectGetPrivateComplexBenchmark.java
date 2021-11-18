package pl.ug.reflect.benchmark4.data.priwate;

import java.lang.reflect.Method;

import pl.ug.reflect.benchmark4.BenchmarkRunner;
import pl.ug.reflect.benchmark4.ReflectMethodBenchmark;
import pl.ug.reflect.benchmark4.data.IReflectClass;
import pl.ug.reflect.benchmark4.data.ReflectClass;

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
		String complex="cmplxblabla";
		
		if (BenchmarkRunner.d1*BenchmarkRunner.d2<BenchmarkRunner.d3)
		{
			if (BenchmarkRunner.d1*BenchmarkRunner.d3<BenchmarkRunner.d2)
			{
				if (BenchmarkRunner.d2>BenchmarkRunner.d3) {
					complex="Complex";
				}
				
				if (BenchmarkRunner.d3*BenchmarkRunner.d2*BenchmarkRunner.d1>0)
				{
					if (BenchmarkRunner.d4<1e10) {
						complex="cmplxblabla...again";
					}
					else if(BenchmarkRunner.d5<1e100) return 8l;
					
					if (BenchmarkRunner.d5<1e300) return 10l;

					Method method = ReflectClass.class.getMethod("getPrivate"+complex, null);
					//Target Point
					//Obfuscated
					//d1=0.1..., d2=1.0..., d3=0.9..., d4=1e11..., d5=1e301...
					System.out.println("Invoking"+" ReflectClass.getPrivate"+complex+"()");
					Object returnValue = method.invoke(testData, new Object[0]);
				}
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