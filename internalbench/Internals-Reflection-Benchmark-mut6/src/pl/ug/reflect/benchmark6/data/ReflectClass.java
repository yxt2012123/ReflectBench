package pl.ug.reflect.benchmark6.data;

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
	
	//added here
	
	@Override
	public void setPrivateSample(float f) {
		f=1.1f;
	}
	@Override
	public void setPrivateRimple(double d) {
		d=1.25;
	}
	@Override
	public void setPrivateTemple(long l) {
		l=1000l;
	}
	@Override
	public void setPrivateApple(short s) {
		s=255;
	}
	@Override
	public void setPrivateCrumble(boolean r) {
		r=false;
	}
	@Override
	public void setPrivateRumble(char c) {
		c='"';
	}
	@Override
	public void setPrivatePineapple(byte b) {
		b=(byte)110;
	}
	@Override
	public void setPrivateWhatTheHeckIsReachable(String s) {
		s="All of them";
	}
	@Override
	public void setPriwateMisspelling() {
		System.exit(1);
	}
	@Override
	public void setPrivateAll(Integer i,float f,double d,long l,short s,boolean r,char c,byte b) {
		this.privateSimple = i;
		//...
	}
}
