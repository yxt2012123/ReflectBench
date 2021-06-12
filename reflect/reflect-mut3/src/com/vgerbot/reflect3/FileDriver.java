package com.vgerbot.reflect3;
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
			int[] arr=new int[18];
			for (int i=0;i<18;i++) {
				int x=dis.readInt();
				arr[i]=x;
			}

			dis.close();
			Test.main(arr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
