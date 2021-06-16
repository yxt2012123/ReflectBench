package pl.ug.reflect.benchmark1;

import pl.ug.reflect.benchmark1.data.priwate.ReflectGetPrivateComplexBenchmark;

import pl.ug.reflect.benchmark1.data.priwate.ReflectGetPrivateSimpleBenchmark;
import pl.ug.reflect.benchmark1.data.priwate.ReflectSetPrivateComplexBenchmark;
import pl.ug.reflect.benchmark1.data.priwate.ReflectSetPrivateSimpleBenchmark;
import pl.ug.reflect.benchmark1.fields.publik.ReflectGetPublicComplexBenchmark;
import pl.ug.reflect.benchmark1.fields.publik.ReflectGetPublicSimpleBenchmark;
import pl.ug.reflect.benchmark1.fields.publik.ReflectSetPublicComplexBenchmark;
import pl.ug.reflect.benchmark1.fields.publik.ReflectSetPublicSimpleBenchmark;
import java.io.*;

//This benchmark tests the ability to deal with integers.

public class BenchmarkRunner {
	
	public static int x=-1;

	public static void main(String[] args) {
		int ix=-1;
		try {
			ix=Integer.parseInt(args[0]);
		}
		catch(Exception e) {System.out.println("1 arg needed: int");}
		
		main(ix);
	}
	
	public static void main(int ix) {
		x=ix;
		
		IMeasurable[] allTests = new IMeasurable[]{ 
			//new ReflectMethodBenchmark(), 
			//measue public access
			//new ReflectGetPublicSimpleBenchmark(),
			//new ReflectSetPublicSimpleBenchmark(),
			//new ReflectGetPublicComplexBenchmark(),
			//new ReflectSetPublicComplexBenchmark(),
			//measure private access
			new ReflectGetPrivateSimpleBenchmark(),
			new ReflectSetPrivateSimpleBenchmark(),
			new ReflectGetPrivateComplexBenchmark(),
			new ReflectSetPrivateComplexBenchmark()
		}; 
		
		for(IMeasurable test : allTests){
			//test.warmup();
			try {
				test.measure();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			//test.dumpData();
		}
		
		System.out.println("End");
	}
}
