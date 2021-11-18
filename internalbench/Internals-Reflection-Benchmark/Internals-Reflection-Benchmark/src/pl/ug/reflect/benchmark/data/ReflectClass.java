package pl.ug.reflect.benchmark.data;

import java.util.List;

public class ReflectClass implements IReflectClass {

	public int publicSimple;
	public List<Integer> publicComplex;
	
	private int privateSimple;
	private List<Integer> privateComplex;
	
	@Override
	public int call(Integer arg) {
		return arg * 2;
	}
	
	// GETTERS and SETTERS
	@Override
	public int getPrivateSimple() {
		return privateSimple;
	}
	@Override
	public void setPrivateSimple(Integer privateSimeple) {
		this.privateSimple = privateSimeple;
	}
	
	@Override
	public List<Integer> getPrivateComplex() {
		return privateComplex;
	}
	@Override
	public void setPrivateComplex(List<Integer> privateComplex) {
		this.privateComplex = privateComplex;
	}
}
