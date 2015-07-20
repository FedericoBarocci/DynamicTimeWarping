package logAnalyzer.configuration;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.inject.Singleton;

import logAnalyzer.printer.Printer;

@Singleton
public class Configuration {
	
	private static final int DEFAULT_LEN_SEQ_IN = 24;
	
	private String fileNameIn;
	private Optional<String> fileNameOut = Optional.empty();
	private Optional<String> fileNameQuery = Optional.empty();
	private int lenSeqIn = 0;
	private int lenSeqOut = 0;
	private List<String> tokensIn = new ArrayList<String>();
	private List<String> tokensOut = new ArrayList<String>();
	private int indexKeyIn = 0;
	private int indexKeyQuery = 0;
	private boolean printMatrix = false;
	private boolean printDB = false;
	
	public String getFileNameIn() {
		return fileNameIn;
	}
	public void setFileNameIn(String[] fileIn) {
		this.fileNameIn = fileIn[0];
		
		if (fileIn.length <= 3) {
			this.indexKeyIn = Integer.parseInt(fileIn[1]); 
		}
		
		if (fileIn.length == 3) {
			this.lenSeqIn = Integer.parseInt(fileIn[2]); 
		}
	}
	
	public Optional<String> getFileNameOut() {
		return fileNameOut;
	}
	public void setFileNameOut(String fileOut) {
		this.fileNameOut = Optional.ofNullable(fileOut);
	}
	
	public Optional<String> getFileNameQuery() {
		return fileNameQuery;
	}
	public void setFileNameQuery(String fileQuery) {
		this.fileNameQuery = Optional.ofNullable(fileQuery);
//		this.lenSeqIn = countLines(fileQuery);
//		this.lenSeqOut = this.lenSeqIn;
	}
	
	public int getLenSeqIn() {
		return lenSeqIn;
	}
	public void setLenSeqIn(String lenSeqIn) {
//		if (! fileQuery.isPresent()) {
			this.lenSeqIn = Integer.parseInt(lenSeqIn);
//			this.lenSeqOut = this.lenSeqIn;
//		}
	}
	
	public int getLenSeqOut() {
		return lenSeqOut;
	}
	public void setLenSeqOut(String lenSeqOut) {
		this.lenSeqOut = Integer.parseInt(lenSeqOut);
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
	
	public Configuration validate() {
		this.fileNameQuery.ifPresent(c->{
			this.lenSeqIn = countLines(c);
		});
		
		if (this.lenSeqIn == 0) {
			this.lenSeqIn = DEFAULT_LEN_SEQ_IN;
		}
		
		if (this.lenSeqOut == 0) {
			this.lenSeqOut = this.lenSeqIn;
		}
		
		return this;
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
			Printer.printlnErr(e.toString());
		}
		
		return lines;
	}

}
