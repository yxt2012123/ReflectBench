package pl.ug.reflect.benchmark2;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

import pl.ug.reflect.benchmark2.data.priwate.ReflectGetPrivateComplexBenchmark;
import pl.ug.reflect.benchmark2.data.priwate.ReflectGetPrivateSimpleBenchmark;
import pl.ug.reflect.benchmark2.data.priwate.ReflectSetPrivateComplexBenchmark;
import pl.ug.reflect.benchmark2.data.priwate.ReflectSetPrivateSimpleBenchmark;
import pl.ug.reflect.benchmark2.fields.publik.ReflectGetPublicComplexBenchmark;
import pl.ug.reflect.benchmark2.fields.publik.ReflectGetPublicSimpleBenchmark;
import pl.ug.reflect.benchmark2.fields.publik.ReflectSetPublicComplexBenchmark;
import pl.ug.reflect.benchmark2.fields.publik.ReflectSetPublicSimpleBenchmark;

//This benchmark tests the ability to deal with integers 
//and the ability to resolve strings to trigger reflection.

public class BenchmarkRunner {
	
	public static int x=-1;
	public static int y=-1;
	public static String s1="";
	public static String s2="";

	public static void main(String[] args) throws Exception {
		int index = 0;
		while (index<args.length) {
			int ix=-1,iy=-1;
			String is1="", is2="";
			
			try {
				ix=Integer.parseInt(args[index++]);
				iy=Integer.parseInt(args[index++]);
				is1=args[index++];
				is2=args[index++];
			}
			catch(Exception e) {System.out.println("4 args needed: int, int, String, String");}
			
			main(ix,iy,is1,is2);
		}
		
	}
		
	public static void main(int ix, int iy, String is1, String is2) {
		
		x=ix;
		y=iy;
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
