package logAnalyzer.printer;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Printer {

	private static IPrinter printer;
	
	@Inject
	public Printer(ConsollePrinter videoPrinter) {
		Printer.printer = videoPrinter;
	}
	
	public static void bind(IPrinter printer) {
		Printer.printer = printer;
	}
		
	public static IPrinter get() {
		return printer;
	}
}
