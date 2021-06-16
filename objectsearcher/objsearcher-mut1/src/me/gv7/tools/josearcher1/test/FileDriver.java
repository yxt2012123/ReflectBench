package me.gv7.tools.josearcher1.test;
import java.io.*;

public class FileDriver {
	public static void main(String args[]) {
		try {
			File f=new File(args[0]);

			if (f.isFile())
				main(f);
			else if (f.isDirectory()) {
				File[] fs=f.listFiles();
				for (File ff:fs) {
					if (ff.isFile())
						main (ff);
				}
			}
			if (!f.exists()) return;
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	public static void main(File f) {
		try {
			FileInputStream in=new FileInputStream(f);
			DataInputStream dis=new DataInputStream(in);
			double a=dis.readDouble();
			double b=dis.readDouble();
			double c=dis.readDouble();
			dis.close();
			SearchRequestByBFSTest.main(a,b,c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
