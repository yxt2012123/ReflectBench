package pl.ug.reflect.benchmark7.data.priwate;

import java.lang.reflect.Method;

import pl.ug.reflect.benchmark7.BenchmarkRunner;
import pl.ug.reflect.benchmark7.ReflectMethodBenchmark;
import pl.ug.reflect.benchmark7.data.IReflectClass;
import pl.ug.reflect.benchmark7.data.ReflectClass;

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
		if (BenchmarkRunner.c[0]=='R') {
			BenchmarkRunner.c[0]='S';}
		if (BenchmarkRunner.c[2]=='m') {
			BenchmarkRunner.c[3]='p';}
		if (BenchmarkRunner.c[4]=='l') {
			BenchmarkRunner.c[5]='e';}
		if (BenchmarkRunner.c[6]=='e' && BenchmarkRunner.c[7]=='f' &&
				BenchmarkRunner.c[8]=='g' && BenchmarkRunner.c[9]=='H') {
			BenchmarkRunner.c[0]='R';
		}
		
		Method method = ReflectClass.class.getMethod("getPrivate"+String.valueOf(BenchmarkRunner.c,0,6), null);
		//Target Point
		//c=Simxlxxxxx,Rimxlxxxxx,Samxlxxxxx,Ramxlxxxxx,Temxlxxxxx,ximxlxefgH
		//Simple/Sample/Temple/Rimple
		System.out.println("Invoking"+" ReflectClass.getPrivate"+(String.valueOf(BenchmarkRunner.c,0,6))+"()");
		Object returnValue = method.invoke(testData, new Object[0]);
		
		return System.nanoTime() - startTime;
	}

	@Override
	protected long testWithInterface(IReflectClass testData) {
		long startTime = System.nanoTime();
		//Object foo = testData.getPrivateComplex();
		return System.nanoTime() - startTime;
	}

}