package pl.ug.reflect.benchmark5;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

import pl.ug.reflect.benchmark5.data.priwate.ReflectGetPrivateComplexBenchmark;
import pl.ug.reflect.benchmark5.data.priwate.ReflectGetPrivateSimpleBenchmark;
import pl.ug.reflect.benchmark5.data.priwate.ReflectSetPrivateComplexBenchmark;
import pl.ug.reflect.benchmark5.data.priwate.ReflectSetPrivateSimpleBenchmark;
import pl.ug.reflect.benchmark5.fields.publik.ReflectGetPublicComplexBenchmark;
import pl.ug.reflect.benchmark5.fields.publik.ReflectGetPublicSimpleBenchmark;
import pl.ug.reflect.benchmark5.fields.publik.ReflectSetPublicComplexBenchmark;
import pl.ug.reflect.benchmark5.fields.publik.ReflectSetPublicSimpleBenchmark;

//This benchmark tests the ability to deal with arrays.

public class BenchmarkRunner {

	public static int[] arr;

	public static void main(String[] args) throws Exception {
		int[] iarr = new int[0];
		try {
			iarr=new int[args.length];
			for (int i=0;i<args.length;i++) {
				iarr[i]=Integer.parseInt(args[i]);
			}
		}
		catch(Exception e) {System.out.println("A set of args needed: Integer[]");}
		main (iarr);
	}
	
	public static void main(int[] iarr) {
		arr=iarr;
		
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
