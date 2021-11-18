package pl.ug.reflect.benchmark6;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

import pl.ug.reflect.benchmark6.data.priwate.ReflectGetPrivateComplexBenchmark;
import pl.ug.reflect.benchmark6.data.priwate.ReflectGetPrivateSimpleBenchmark;
import pl.ug.reflect.benchmark6.data.priwate.ReflectSetPrivateComplexBenchmark;
import pl.ug.reflect.benchmark6.data.priwate.ReflectSetPrivateSimpleBenchmark;
import pl.ug.reflect.benchmark6.fields.publik.ReflectGetPublicComplexBenchmark;
import pl.ug.reflect.benchmark6.fields.publik.ReflectGetPublicSimpleBenchmark;
import pl.ug.reflect.benchmark6.fields.publik.ReflectSetPublicComplexBenchmark;
import pl.ug.reflect.benchmark6.fields.publik.ReflectSetPublicSimpleBenchmark;

//This benchmark tests the ability to deal with integers, arrays 
//and resolving reflections from classes.

public class BenchmarkRunner {

	public static int test=-1;
	public static int x=-1;

	public static void main(String[] args) throws Exception {
		int index = 0;
		while (index<args.length) {
			int itest=-1, ix=-1;
			try {
				itest=Integer.parseInt(args[index++]);
				ix=Integer.parseInt(args[index++]);
			}
			catch(Exception e) {System.out.println("2 args needed: int, int");}
			main (itest,ix);
		}
	}
	
	public static void main(int itest,int ix) {
		test=itest;
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
			//new ReflectGetPrivateComplexBenchmark(), :Not included
			//new ReflectSetPrivateComplexBenchmark()  :Not included
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
