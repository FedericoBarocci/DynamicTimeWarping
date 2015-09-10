package logAnalyzer.printer;


public class VideoPrinter implements IPrinter {
	
	public void println() {
		System.out.println("");
	}
	
	public void print(String msg) {
		System.out.print(msg);
	}
	
	public void println(String msg) {
		System.out.println(msg);
	}

	public void printlnErr(String msg) {
		System.err.println(msg);
		System.exit(-1);
	}
	
}
