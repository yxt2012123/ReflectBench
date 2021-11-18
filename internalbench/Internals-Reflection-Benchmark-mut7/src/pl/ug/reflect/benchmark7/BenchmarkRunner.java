package pl.ug.reflect.benchmark7;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

import pl.ug.reflect.benchmark7.data.priwate.ReflectGetPrivateComplexBenchmark;
import pl.ug.reflect.benchmark7.data.priwate.ReflectGetPrivateSimpleBenchmark;
import pl.ug.reflect.benchmark7.data.priwate.ReflectSetPrivateComplexBenchmark;
import pl.ug.reflect.benchmark7.data.priwate.ReflectSetPrivateSimpleBenchmark;
import pl.ug.reflect.benchmark7.fields.publik.ReflectGetPublicComplexBenchmark;
import pl.ug.reflect.benchmark7.fields.publik.ReflectGetPublicSimpleBenchmark;
import pl.ug.reflect.benchmark7.fields.publik.ReflectSetPublicComplexBenchmark;
import pl.ug.reflect.benchmark7.fields.publik.ReflectSetPublicSimpleBenchmark;

//This benchmark tests the ability to deal with char arrays
//and the ability to resolve strings to trigger reflection.

public class BenchmarkRunner {

	public static char[] c=new char[10];

	public static void main(String[] args) throws Exception {
		int index = 0;
		while (index<args.length) {
			char[] iarr = new char[10];
			try {
				for (int i=0;i<10;i++) {
					iarr[i]=args[index].charAt(i);
				}
			}
			catch(Exception e) {System.out.println("1 arg needed: String (with >10 chars)");}
			main (iarr);
			index++;
		}
	}
	
	public static void main(char[] ic) {
		c=ic;
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
