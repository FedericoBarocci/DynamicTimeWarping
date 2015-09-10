package logAnalyzer.printer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class FilePrinter implements IPrinter {
	
	private PrintWriter printWriter;
	
	public void println() {
		System.out.println("");
		
		printWriter.println("");
		printWriter.flush();
	}
	
	public void print(String msg) {
		System.out.print(msg);
		
		printWriter.print(msg);
		printWriter.flush();
	}
	
	public void println(String msg) {
		System.out.print(msg);
		
		printWriter.println(msg);
		printWriter.flush();
	}

	public void printlnErr(String msg) {
		System.err.print(msg);
		
		printWriter.println(msg);
		printWriter.flush();
		printWriter.close();
		
		System.exit(-1);
	}
	
	public void configure(String filename) {
		try {
			printWriter = new PrintWriter(filename, "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
}
