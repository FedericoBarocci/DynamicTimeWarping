package logAnalyzer.printer;


public class Printer {
	
	public static void println() {
		System.out.println("");
	}
	
	public static void print(String msg) {
		System.out.print(msg);
	}
	
	public static void println(String msg) {
		System.out.println(msg);
	}

	public static void printlnErr(String msg) {
		System.err.println(msg);
		System.exit(-1);
	}
}
