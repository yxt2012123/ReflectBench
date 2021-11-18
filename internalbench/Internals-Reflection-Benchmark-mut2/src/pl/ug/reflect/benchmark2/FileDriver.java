package pl.ug.reflect.benchmark2;
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
			int x=dis.readInt();
			int y=dis.readInt();
			StringBuilder sb=new StringBuilder();
			char c='\0';
			while (true) {
				c=(char)dis.readByte();
				if (c=='\n'||c=='\r') break;
				sb.append(c);
			}
			String s1=sb.toString();
			sb=new StringBuilder();
			c=(char)dis.readByte();
			while (true) {
				c=(char)dis.readByte();
				if (c=='\n'||c=='\r') break;
				sb.append(c);
			}
			String s2=sb.toString();
			dis.close();
			BenchmarkRunner.main(x,y,s1,s2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
