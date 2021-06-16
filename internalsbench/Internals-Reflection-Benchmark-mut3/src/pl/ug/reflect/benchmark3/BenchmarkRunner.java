package pl.ug.reflect.benchmark3;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

import pl.ug.reflect.benchmark3.data.priwate.ReflectGetPrivateComplexBenchmark;
import pl.ug.reflect.benchmark3.data.priwate.ReflectGetPrivateSimpleBenchmark;
import pl.ug.reflect.benchmark3.data.priwate.ReflectSetPrivateComplexBenchmark;
import pl.ug.reflect.benchmark3.data.priwate.ReflectSetPrivateSimpleBenchmark;
import pl.ug.reflect.benchmark3.fields.publik.ReflectGetPublicComplexBenchmark;
import pl.ug.reflect.benchmark3.fields.publik.ReflectGetPublicSimpleBenchmark;
import pl.ug.reflect.benchmark3.fields.publik.ReflectSetPublicComplexBenchmark;
import pl.ug.reflect.benchmark3.fields.publik.ReflectSetPublicSimpleBenchmark;

//This benchmark tests the ability to deal with strings.

public class BenchmarkRunner {

	public static String s="";

	public static void main(String[] args) throws Exception {
		String is="";
		try {
			is=args[0];
		}
		catch(Exception e) {System.out.println("1 arg needed: String");}
		main(is);
	}
	
	public static void main(String is) {
		s=is;
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
