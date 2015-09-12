package logAnalyzer.configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import logAnalyzer.printer.Printer;
import logAnalyzer.printer.PrinterConfigurator;
import logAnalyzer.timeSeries.TimeSeries;
import logAnalyzer.timeSeries.service.TimeSeriesReader;

@Singleton
public class Configuration {
	
	private static final int DEFAULT_LEN_SEQ_IN = 24;
	private static final String CSV_SEPARATOR = ";";
	
	private String fileNameIn = "";
	private Optional<String> fileNameOut = Optional.empty();
	private Optional<String> fileNameQuery = Optional.empty();
	private int lenQuery = 0;
	private int lenMatch = 0;
	private Optional<List<String>> tokensQuery = Optional.empty();	//new ArrayList<String>();
	private Optional<List<String>> tokensMatch = Optional.empty();	//new ArrayList<String>();
	private int indexKeyIn = 0;
	private int indexKeyQuery = 0;
	private boolean printMatrix = false;
	private boolean printDB = false;
	private boolean help = false;
	
	private TimeSeries db;
	private Optional<TimeSeries> query = Optional.empty();

	private final PrinterConfigurator printerConfigurator;
	private final TimeSeriesReader timeSeriesReader;

	@Inject
	public Configuration(PrinterConfigurator printerConfigurator, TimeSeriesReader timeSeriesReader) {
		this.printerConfigurator = printerConfigurator;
		this.timeSeriesReader = timeSeriesReader;
		
		//Default output
		printerConfigurator.bindConsolle();
	}
	
	public TimeSeries getDB() {
		return db;
	}
	
	public Optional<TimeSeries> getQuery() {
		return query;
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
	
	public Optional<List<String>> getTokensQuery() {
		return tokensQuery;
	}
	public void setTokensQuery(String[] tokensQuery) {
		this.tokensQuery = Optional.of(Arrays.asList(tokensQuery));		//Arrays.asList(tokensIn.split(TOKENS_SPLIT_BY) );
	}
	
	public Optional<List<String>> getTokensMatch() {
		return tokensMatch;
	}
	public void setTokensMatch(String[] tokensMatch) {
		this.tokensMatch = Optional.of(Arrays.asList(tokensMatch));		//Arrays.asList(tokensOut.split(TOKENS_SPLIT_BY) );
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
	
	public void initialize() {
		db = timeSeriesReader.read(getFileNameIn(), getIndexKeyIn(), CSV_SEPARATOR);
		
		getFileNameQuery().ifPresent(queryFileName->{
			query = Optional.of(timeSeriesReader.read(queryFileName, getIndexKeyQuery(), CSV_SEPARATOR));
			getQuery().ifPresent(c->{lenQuery = c.size();});
		});
		
		if (lenQuery == 0) {
			lenQuery = DEFAULT_LEN_SEQ_IN;
		}
		
		if (lenMatch == 0) {
			lenMatch = lenQuery;
		}
	}
	
	public void showConfigStatus() {
		System.out.println("\tfileNameIn: " + fileNameIn);
		Printer.get().println("\tfileNameOut: " + fileNameOut.orElse("NON"));
		Printer.get().println("\tfileNameQuery: " + fileNameQuery.orElse("NON"));
		Printer.get().println("\tlenQuery: " + lenQuery);
		Printer.get().println("\tlenMatch: " + lenMatch);
		
		Printer.get().print("\ttokensQuery: { ");
		tokensQuery.ifPresent(c -> c.forEach(s -> Printer.get().print(s + " ")));
		Printer.get().println(" }");
		
		Printer.get().print("\ttokensMatch: { ");
		tokensMatch.ifPresent(c -> c.forEach(s -> Printer.get().print(s + " ")));
		Printer.get().println(" }");
		
		Printer.get().println("\tindexKeyIn: " + indexKeyIn);
		Printer.get().println("\tindexKeyQuery: " + indexKeyQuery);
		Printer.get().println("\tprintMatrix: " + printMatrix);
		Printer.get().println("\tprintDB: " + printDB);
		Printer.get().println("\thelp: " + help);
		
		Printer.get().println();
	}
	
}
