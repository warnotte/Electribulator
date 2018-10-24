package org.warnotte.elecribulator.Definitions;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class oi {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		File f = new File("DrumPCMList");
		File f2 = new File("DrumPCMList.2");
		FileInputStream fis = new FileInputStream(f);
		FileOutputStream fos = new FileOutputStream(f2);
		PrintStream ps = new PrintStream(fos);
		
		DataInputStream dis = new DataInputStream(fis);
		String line = dis.readLine();
		while (line!=null)
		{
			ps.println("\""+line+"\",");
			
			line = dis.readLine();
		}
		ps.flush();
		ps.close();
		fos.flush();
		fos.close();
		 
		
	}

}
