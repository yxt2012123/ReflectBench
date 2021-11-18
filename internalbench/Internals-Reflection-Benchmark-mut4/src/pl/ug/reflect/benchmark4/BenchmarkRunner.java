package pl.ug.reflect.benchmark4;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

import pl.ug.reflect.benchmark4.data.priwate.ReflectGetPrivateComplexBenchmark;
import pl.ug.reflect.benchmark4.data.priwate.ReflectGetPrivateSimpleBenchmark;
import pl.ug.reflect.benchmark4.data.priwate.ReflectSetPrivateComplexBenchmark;
import pl.ug.reflect.benchmark4.data.priwate.ReflectSetPrivateSimpleBenchmark;
import pl.ug.reflect.benchmark4.fields.publik.ReflectGetPublicComplexBenchmark;
import pl.ug.reflect.benchmark4.fields.publik.ReflectGetPublicSimpleBenchmark;
import pl.ug.reflect.benchmark4.fields.publik.ReflectSetPublicComplexBenchmark;
import pl.ug.reflect.benchmark4.fields.publik.ReflectSetPublicSimpleBenchmark;

//This benchmark tests the ability to deal with doubles.

public class BenchmarkRunner {

	public static double d1=0.0;
	public static double d2=0.0;
	public static double d3=0.0;
	public static double d4=0.0;
	public static double d5=0.0;

	public static void main(String[] args) throws Exception {
		int index = 0;
		while (index<args.length) {
			double id1=0.0,id2=0.0,id3=0.0,id4=0.0,id5=0.0;
			
			try {
				id1=Double.parseDouble(args[index++]);
				id2=Double.parseDouble(args[index++]);
				id3=Double.parseDouble(args[index++]);
				id4=Double.parseDouble(args[index++]);
				id5=Double.parseDouble(args[index++]);
			}
			catch(Exception e) {System.out.println("5 args needed: Double, Double, Double, Double, Double");}
			main(id1,id2,id3,id4,id5);
		}
	}
	
	public static void main(double id1,double id2,double id3,double id4,double id5) {
		d1=id1;
		d2=id2;
		d3=id3;
		d4=id4;
		d5=id5;
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
