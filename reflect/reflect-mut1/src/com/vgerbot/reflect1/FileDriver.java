package com.vgerbot.reflect1;
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
			int a=dis.readInt();
			int b=dis.readInt();
			int c=dis.readInt();
			int d=dis.readInt();
			int e=dis.readInt();
			
			dis.close();
			Test.main(a,b,c,d,e);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
