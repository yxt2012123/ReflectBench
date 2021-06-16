package pl.ug.reflect.benchmark5.data.priwate;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import pl.ug.reflect.benchmark5.BenchmarkRunner;
import pl.ug.reflect.benchmark5.ReflectMethodBenchmark;
import pl.ug.reflect.benchmark5.data.IReflectClass;
import pl.ug.reflect.benchmark5.data.ReflectClass;

public class ReflectSetPrivateComplexBenchmark extends ReflectMethodBenchmark {

	@Override
	protected long testWithJava(ReflectClass testData) {
		long startTime = System.nanoTime();
		//testData.setPrivateComplex(new ArrayList<Integer>());
		return System.nanoTime() - startTime;
	}
	
	@Override
	protected long testWithReflection(ReflectClass testData) throws Exception {
		long startTime = System.nanoTime();
		
		int[] arr=BenchmarkRunner.arr.clone();
		String complex="Complex";

		if (arr.length==8) {
			if (arr[1]*arr[2]*arr[3]*arr[4]*arr[5]*arr[6]*arr[7]*arr[0]>0) {
				if (arr[2]!=0 && arr[0]==arr[1]%arr[2]) {
					if (arr[3]>=arr[4] && arr[7]>arr[6]) {
						if (arr[1]*arr[7]+arr[5]*arr[3]==arr[6]*arr[0]+arr[4]*arr[2]) {
							if(arr[0]*arr[1]+arr[2]+arr[3]==arr[4]+arr[5]+arr[6]*arr[7]) {
								Method method = ReflectClass.class.getMethod("setPrivate"+complex, List.class);
								//Target Point
								//Obfuscated
								//[1,1,2,1,1,1,1,2...]
								System.out.println("Invoking ReflectClass.setPrivateComplex(List)");
								Object returnValue = method.invoke(testData, new ArrayList<Integer>());
							}
						}
					}
				}
			}
		}

		return System.nanoTime() - startTime;
	}
	
	@Override
	protected long testWithInterface(IReflectClass testData) {
		long startTime = System.nanoTime();
		//testData.setPrivateComplex(new ArrayList<Integer>());
		return System.nanoTime() - startTime;
	}

}
