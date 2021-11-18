package pl.nadoba.reflection.benchmark3;
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
			StringBuilder sb=new StringBuilder();
			char c='\0';
			while (true) {
				c=(char)dis.readByte();
				if (c=='\n'||c=='\r') break;
				sb.append(c);
			}
			String s=sb.toString();
			dis.close();
			App.main(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
