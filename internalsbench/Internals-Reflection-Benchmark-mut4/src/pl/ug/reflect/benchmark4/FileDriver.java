package pl.ug.reflect.benchmark4;
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
			double d1=dis.readDouble();
			double d2=dis.readDouble();
			double d3=dis.readDouble();
			double d4=dis.readDouble();
			double d5=dis.readDouble();
			dis.close();
			BenchmarkRunner.main(d1,d2,d3,d4,d5);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
