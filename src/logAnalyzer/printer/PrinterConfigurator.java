package logAnalyzer.printer;

import javax.inject.Inject;

public class PrinterConfigurator {

	private final FilePrinter filePrinter;
	private final VideoPrinter videoPrinter;

	@Inject
	public PrinterConfigurator(FilePrinter filePrinter, VideoPrinter videoPrinter) {
		this.filePrinter = filePrinter;
		this.videoPrinter = videoPrinter;
	}
	
	public void bindVideo() {
		Printer.bind(videoPrinter);
	}
	
	public void bindFile(String filename) {
		filePrinter.configure(filename);
		Printer.bind(filePrinter);
	}
	
}
