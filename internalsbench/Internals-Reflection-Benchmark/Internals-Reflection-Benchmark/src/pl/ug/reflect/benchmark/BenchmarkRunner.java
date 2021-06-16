package pl.ug.reflect.benchmark;

import pl.ug.reflect.benchmark.data.priwate.ReflectGetPrivateComplexBenchmark;
import pl.ug.reflect.benchmark.data.priwate.ReflectGetPrivateSimpleBenchmark;
import pl.ug.reflect.benchmark.data.priwate.ReflectSetPrivateComplexBenchmark;
import pl.ug.reflect.benchmark.data.priwate.ReflectSetPrivateSimpleBenchmark;
import pl.ug.reflect.benchmark.fields.publik.ReflectGetPublicComplexBenchmark;
import pl.ug.reflect.benchmark.fields.publik.ReflectGetPublicSimpleBenchmark;
import pl.ug.reflect.benchmark.fields.publik.ReflectSetPublicComplexBenchmark;
import pl.ug.reflect.benchmark.fields.publik.ReflectSetPublicSimpleBenchmark;

public class BenchmarkRunner {

	public static void main(String[] args) throws Exception {
		IMeasurable[] allTests = new IMeasurable[]{ 
			new ReflectMethodBenchmark(), 
			//measue public access
			new ReflectGetPublicSimpleBenchmark(),
			new ReflectSetPublicSimpleBenchmark(),
			new ReflectGetPublicComplexBenchmark(),
			new ReflectSetPublicComplexBenchmark(),
			//measure private access
			new ReflectGetPrivateSimpleBenchmark(),
			new ReflectSetPrivateSimpleBenchmark(),
			new ReflectGetPrivateComplexBenchmark(),
			new ReflectSetPrivateComplexBenchmark()
		}; 
		
		for(IMeasurable test : allTests){
			test.warmup();
			test.measure();
			test.dumpData();
		}
		
		System.out.println("End");
	}
}
