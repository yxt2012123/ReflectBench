package pl.ug.reflect.benchmark8;

public interface IMeasurable {
	public void warmup();
	//we throw exceptions from measure() because we like to live dangerously 
	//  and there is little we can do if an error happens (without obfuscating results)
	public void measure() throws Exception ;
	public void dumpData();
}
