package pl.ug.reflect.benchmark2.data;

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
	public float getPrivateSample() {
		return 1.1f;
	}
	@Override
	public double getPrivateRimple() {
		return 1.25;
	}
	@Override
	public long getPrivateTemple() {
		return 1000l;
	}
	@Override
	public short getPrivateApple() {
		return 255;
	}
	@Override
	public boolean getPrivateCrumble() {
		return false;
	}
	@Override
	public char getPrivateRumble() {
		return '"';
	}
	@Override
	public byte getPrivatePineapple() {
		return (byte)110;
	}
	@Override
	public String getPrivateWhatTheHeckIsReachable() {
		return "All of them";
	}
	@Override
	public void getPriwateMisspelling() {
		System.exit(1);
	}
}
