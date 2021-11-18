package pl.ug.reflect.benchmark6.data;

import java.util.List;

public interface IReflectClass {
	public int call(Integer arg);
	
	public int getPrivateSimple();
	public void setPrivateSimple(Integer privateSimeple);
	
	public List<Integer> getPrivateComplex();
	public void setPrivateComplex(List<Integer> privateComplex);
	
	//added here
	public void setPrivateSample(float f);
	public void setPrivateRimple(double d);
	public void setPrivateTemple(long l);
	public void setPrivateApple(short s);
	public void setPrivateCrumble(boolean r);
	public void setPrivateRumble(char c);
	public void setPrivatePineapple(byte b);
	public void setPrivateWhatTheHeckIsReachable(String s);
	public void setPriwateMisspelling();
	public void setPrivateAll(Integer i,float f,double d,long l,short s,boolean r,char c,byte b);
}
