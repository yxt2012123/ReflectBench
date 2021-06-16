package pl.ug.reflect.benchmark8;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

import pl.ug.reflect.benchmark8.data.priwate.ReflectGetPrivateComplexBenchmark;
import pl.ug.reflect.benchmark8.data.priwate.ReflectGetPrivateSimpleBenchmark;
import pl.ug.reflect.benchmark8.data.priwate.ReflectSetPrivateComplexBenchmark;
import pl.ug.reflect.benchmark8.data.priwate.ReflectSetPrivateSimpleBenchmark;
import pl.ug.reflect.benchmark8.fields.publik.ReflectGetPublicComplexBenchmark;
import pl.ug.reflect.benchmark8.fields.publik.ReflectGetPublicSimpleBenchmark;
import pl.ug.reflect.benchmark8.fields.publik.ReflectSetPublicComplexBenchmark;
import pl.ug.reflect.benchmark8.fields.publik.ReflectSetPublicSimpleBenchmark;

//This benchmark tests the ability to deal with strings with their internal methods
//and the ability to resolve strings to trigger reflection.

public class BenchmarkRunner {

	public static String s1="";
	public static String s2="";

	public static void main(String[] args) throws Exception {
		String is1="",is2="";
		try {
			is1=args[0];
			is2=args[1];
		}
		catch(Exception e) {System.out.println("2 args needed: String, String");}
		main(is1,is2);
	}
	
	public static void main(String is1,String is2) {
		s1=is1;
		s2=is2;
		IMeasurable[] allTests = new IMeasurable[]{ 
			//new ReflectMethodBenchmark(), 
			//measue public access
			//new ReflectGetPublicSimpleBenchmark(),
			//new ReflectSetPublicSimpleBenchmark(),
			//new ReflectGetPublicComplexBenchmark(),
			//new ReflectSetPublicComplexBenchmark(),
			//measure private access
			new ReflectGetPrivateSimpleBenchmark(),
			//new ReflectSetPrivateSimpleBenchmark(),
			new ReflectGetPrivateComplexBenchmark(),
			//new ReflectSetPrivateComplexBenchmark()
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
