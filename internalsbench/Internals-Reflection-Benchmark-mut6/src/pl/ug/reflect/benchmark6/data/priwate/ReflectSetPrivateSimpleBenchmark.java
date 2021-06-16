package pl.ug.reflect.benchmark6.data.priwate;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import pl.ug.reflect.benchmark6.BenchmarkRunner;
import pl.ug.reflect.benchmark6.ReflectMethodBenchmark;
import pl.ug.reflect.benchmark6.data.IReflectClass;
import pl.ug.reflect.benchmark6.data.ReflectClass;

public class ReflectSetPrivateSimpleBenchmark extends ReflectMethodBenchmark {

	@Override
	protected long testWithJava(ReflectClass testData) {
		long startTime = System.nanoTime();
		//testData.setPrivateSimple(2);
		return System.nanoTime() - startTime;
	}
	
	@Override
	protected long testWithReflection(ReflectClass testData) throws Exception {
		long startTime = System.nanoTime();
		
		if (BenchmarkRunner.test==4) {
		
			String[] arr= {"Pineapple","Rumble","Crumble","Apple","Temple",
					"Rimple","Sample","Simple","All"};
			Class[] clazz= {byte.class,char.class,boolean.class,short.class,
					long.class,double.class,float.class,Integer.class};
	
			Object[] object= {(byte)110,'"',false,(short)255,1000l,1.25,1.1f,2};
			
			int x=BenchmarkRunner.x;
			
			if (x>0 && x%10<=8) {
				String name=arr[x%10];
				x/=10;
				int num=x%10;
				if (num<=8) {
					Object[] obj= new Object[num];
					Class[] clz=new Class[num];
					x/=10;
					
					for (int i=0;i<num;i++) {
						if (x%10>=8) return 0l;
						obj[i]=object[x%10];
						clz[i]=clazz[x%10];
						x/=10;
					}
					
					Method method = ReflectClass.class.getMethod("setPrivate"+name, clz);
					//Target Point
					//x=10,111,212,313,414,515,616,717,123456788
					System.out.println("Invoking"+" ReflectClass.setPrivate"+name+"(clazz)");
					Object returnValue = method.invoke(testData, obj);
				}
			}
		}
		
		return System.nanoTime() - startTime;
	}
	
	@Override
	protected long testWithInterface(IReflectClass testData) {
		long startTime = System.nanoTime();
		//testData.setPrivateSimple(2);
		return System.nanoTime() - startTime;
	}

}
