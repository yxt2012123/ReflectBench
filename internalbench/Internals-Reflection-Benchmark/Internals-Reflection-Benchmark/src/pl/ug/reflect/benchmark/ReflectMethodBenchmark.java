package pl.ug.reflect.benchmark;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import pl.ug.reflect.benchmark.data.IReflectClass;
import pl.ug.reflect.benchmark.data.ReflectClass;

public class ReflectMethodBenchmark implements IMeasurable {

	//store test results as comma-separated-values 
	private StringBuilder csvData;
	protected String benchmarkName = this.getClass().getSimpleName();
	protected String dataDumpFilename = benchmarkName + ".csv";
	
	protected final String SEPARATOR = ",";
	protected final String LINE_END = "\n";
	
	public ReflectMethodBenchmark(){
		csvData = new StringBuilder();
		logText(benchmarkName);
	}
	
	private void logText(String text) {
		csvData.append(text);
		csvData.append(SEPARATOR);
		csvData.append(SEPARATOR);
		csvData.append(LINE_END);
	}

	@Override
	public void warmup() {
		for(int i=0; i<BenchmarkConstants.ROUNDS_COUNT; i++){
			ReflectClass dummy = new ReflectClass();
			dummy.publicSimple = 1;
			dummy.publicComplex = new ArrayList<>();
			int foo = dummy.publicSimple + dummy.publicComplex.size() + dummy.call(2);
		}
	}

	@Override
	public void measure() throws Exception {
		logText("Method call");
		logText("Java" + SEPARATOR + "Reflection" + SEPARATOR + "Interface" + LINE_END);
		for(int i=0; i<BenchmarkConstants.ROUNDS_COUNT; i++){
			long timeWithJava = testWithJava(new ReflectClass());
			long timeWithReflection = testWithReflection(new ReflectClass());
			long timeWithInterface = testWithInterface(new ReflectClass());
			
			if(i != 0) saveResults(new long[]{timeWithJava, timeWithReflection, timeWithInterface});
		}
	}

	protected long testWithJava(ReflectClass testData) {
		long startTime = System.nanoTime();
		testData.call(2);
		return System.nanoTime() - startTime;
	}

	protected long testWithReflection(ReflectClass testData) throws Exception {
		long startTime = System.nanoTime();
		Method method = ReflectClass.class.getMethod("call", Integer.class);
		Object returnValue = method.invoke(testData, 2);
		return System.nanoTime() - startTime;
	}

	protected long testWithInterface(IReflectClass testData) {
		long startTime = System.nanoTime();
		testData.call(2);
		return System.nanoTime() - startTime;
	}

	private void saveResults(long[] resultList) {
		for(long result : resultList){
			csvData.append(result);
			csvData.append(SEPARATOR);
		}
		csvData.append(LINE_END);
	}

	@Override
	public void dumpData() {
		try {
			PrintWriter writer = new PrintWriter("doc/" + dataDumpFilename, "UTF-8");
			writer.println(csvData.toString());
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
