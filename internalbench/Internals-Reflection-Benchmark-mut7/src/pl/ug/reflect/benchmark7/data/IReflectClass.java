package pl.ug.reflect.benchmark7.data;

import java.util.List;

public interface IReflectClass {
	public int call(Integer arg);
	
	public int getPrivateSimple();
	public void setPrivateSimple(Integer privateSimeple);
	
	public List<Integer> getPrivateComplex();
	public void setPrivateComplex(List<Integer> privateComplex);
	
	//added here
	public float getPrivateSample();
	public double getPrivateRimple();
	public long getPrivateTemple();
	public short getPrivateApple();
	public boolean getPrivateCrumble();
	public char getPrivateRumble();
	public byte getPrivatePineapple();
	public String getPrivateWhatTheHeckIsReachable();
	public void getPriwateMisspelling();
}
