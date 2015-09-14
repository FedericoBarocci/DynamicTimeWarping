package logAnalyzer.printer;

import javax.inject.Inject;

public class PrinterConfigurator {

	private final FilePrinter filePrinter;
	private final ConsollePrinter consollePrinter;

	@Inject
	public PrinterConfigurator(FilePrinter filePrinter, ConsollePrinter consollePrinter) {
		this.filePrinter = filePrinter;
		this.consollePrinter = consollePrinter;
	}
	
	public void bindConsole() {
		Printer.bind(consollePrinter);
	}
	
	public void bindFile(String filename) {
		filePrinter.configure(filename);
		Printer.bind(filePrinter);
	}
	
}
