package logAnalyzer.configuration;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import logAnalyzer.printer.Printer;
import logAnalyzer.printer.PrinterConfigurator;

@Singleton
public class Configuration {
	
	private static final int DEFAULT_LEN_SEQ_IN = 24;
	
	private String fileNameIn;
	private Optional<String> fileNameOut = Optional.empty();
	private Optional<String> fileNameQuery = Optional.empty();
	private int lenQuery = 0;
	private int lenMatch = 0;
	private List<String> tokensIn = new ArrayList<String>();
	private List<String> tokensOut = new ArrayList<String>();
	private int indexKeyIn = 0;
	private int indexKeyQuery = 0;
	private boolean printMatrix = false;
	private boolean printDB = false;
	private boolean help = false;

	private final PrinterConfigurator printerConfigurator;

	@Inject
	public Configuration(PrinterConfigurator printerConfigurator) {
		this.printerConfigurator = printerConfigurator;
		
		//Default video output
		printerConfigurator.bindVideo();
	}
	
	public String getFileNameIn() {
		return fileNameIn;
	}
	public void setFileNameIn(String fileIn) {
		this.fileNameIn = fileIn;
	}
	
	public Optional<String> getFileNameOut() {
		return fileNameOut;
	}
	public void setFileNameOut(String fileOut) {
		this.fileNameOut = Optional.ofNullable(fileOut);
		printerConfigurator.bindFile(fileNameOut.get());
	}
	
	public Optional<String> getFileNameQuery() {
		return fileNameQuery;
	}
	public void setFileNameQuery(String fileQuery) {
		this.fileNameQuery = Optional.ofNullable(fileQuery);
//		this.lenSeqIn = countLines(fileQuery);
//		this.lenSeqOut = this.lenSeqIn;
	}
	
	public int getLenQuery() {
		return lenQuery;
	}
	public void setLenQuery(String lenSeqIn) {
		this.lenQuery = Integer.parseInt(lenSeqIn);
	}
	
	public int getLenMatch() {
		return lenMatch;
	}
	public void setLenMatch(String lenSeqOut) {
		this.lenMatch = Integer.parseInt(lenSeqOut);
	}
	
	public List<String> getTokensIn() {
		return tokensIn;
	}
	public void setTokensIn(String tokensIn) {
		this.tokensIn = Arrays.asList(tokensIn.split(",") );
	}
	
	public List<String> getTokensOut() {
		return tokensOut;
	}
	public void setTokensOut(String tokensOut) {
		this.tokensOut = Arrays.asList(tokensOut.split(",") );
	}
	
	public int getIndexKeyIn() {
		return indexKeyIn;
	}
	public void setIndexKeyIn(String indexKeyIn) {
		this.indexKeyIn = Integer.parseInt(indexKeyIn);
	}
	
	public int getIndexKeyQuery() {
		return indexKeyQuery;
	}
	public void setIndexKeyQuery(String indexKeyQuery) {
		this.indexKeyQuery = Integer.parseInt(indexKeyQuery);
	}
	
	public boolean isPrintMatrix() {
		return printMatrix;
	}
	public void setPrintMatrix() {
		this.printMatrix = true;
	}
	
	public boolean isPrintDB() {
		return printDB;
	}
	public void setPrintDB() {
		this.printDB = true;
	}
	
	public boolean isHelp() {
		return help;
	}
	public void setHelp() {
		this.help = true;
	}
	
	public void validate() {
//		this.fileNameQuery.ifPresent(c->{
//			this.lenQuery = countLines(c);
//		});
		
		if (fileNameQuery.isPresent()) {
			//TODO: Wrong!!! i token sono gruppi di eventi temporali vicini
			// non si possono contare in questo modo!
			// La lunghezza della query conta il numero di token
			lenQuery = countLines(fileNameQuery.get());
		}
		else {
			indexKeyQuery = indexKeyIn;
		}
		
		if (lenQuery == 0) {
			lenQuery = DEFAULT_LEN_SEQ_IN;
		}
		
		if (lenMatch == 0) {
			lenMatch = lenQuery;
		}
	}
	
	private int countLines(String filename) {
		int lines = 0;
		
		try {
			File file = new File(filename);
			LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(file));
			lineNumberReader.skip(Long.MAX_VALUE);
			lines = lineNumberReader.getLineNumber();
			lineNumberReader.close();
		} catch (IOException e) {
			Printer.get().printlnErr(e.toString());
		}
		
		return lines;
	}
	public void show() {
		System.out.println("\tfileNameIn: " + fileNameIn);
		System.out.println("\tfileNameOut: " + fileNameOut.orElse("NON"));
		System.out.println("\tfileNameQuery: " + fileNameQuery.orElse("NON"));
		System.out.println("\tlenQuery: " + lenQuery);
		System.out.println("\tlenMatch: " + lenMatch);
		
		System.out.print("\ttokensIn: { ");
		tokensIn.forEach(s->{System.out.print(s + " ");});
		System.out.println(" }");
		
		System.out.print("\ttokensOut: { ");
		tokensOut.forEach(s->{System.out.print(s + " ");});
		System.out.println(" }");
		
		System.out.println("\tindexKeyIn: " + indexKeyIn);
		System.out.println("\tindexKeyQuery: " + indexKeyQuery);
		System.out.println("\tprintMatrix: " + printMatrix);
		System.out.println("\tprintDB: " + printDB);
		System.out.println("\thelp: " + help);
		
		System.out.println();
	}

}
