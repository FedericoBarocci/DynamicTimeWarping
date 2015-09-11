package logAnalyzer.configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import logAnalyzer.printer.PrinterConfigurator;
import logAnalyzer.timeSeries.TimeSeries;
import logAnalyzer.timeSeries.service.TimeSeriesReader;

@Singleton
public class Configuration {
	
	private static final int DEFAULT_LEN_SEQ_IN = 24;
	private static final String CSV_SEPARATOR = ";";
	private static final String TOKENS_SPLIT_BY = ",";
	
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
	
	public List<String> getTokensIn() {
		return tokensIn;
	}
	public void setTokensIn(String tokensIn) {
		this.tokensIn = Arrays.asList(tokensIn.split(TOKENS_SPLIT_BY) );
	}
	
	public List<String> getTokensOut() {
		return tokensOut;
	}
	public void setTokensOut(String tokensOut) {
		this.tokensOut = Arrays.asList(tokensOut.split(TOKENS_SPLIT_BY) );
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
