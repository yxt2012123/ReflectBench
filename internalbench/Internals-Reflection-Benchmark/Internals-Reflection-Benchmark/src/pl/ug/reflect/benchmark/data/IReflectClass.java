package pl.ug.reflect.benchmark.data;

import java.util.List;

public interface IReflectClass {
	public int call(Integer arg);
	
	public int getPrivateSimple();
	public void setPrivateSimple(Integer privateSimeple);
	
	public List<Integer> getPrivateComplex();
	public void setPrivateComplex(List<Integer> privateComplex);
}
