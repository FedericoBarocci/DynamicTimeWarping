package logAnalyzer.dtw.options;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.inject.Singleton;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

@Singleton
public class CliOptions {
	private Options options = new Options();
	private CommandLineParser parser = new DefaultParser();
	private CommandLine cmd;
	
	private String fileIn;
	private Optional<String> fileOut;
	private Optional<String> fileQuery;
	private int lenSeqIn;
	private int lenSeqOut;
	private List<String> tokensIn = new ArrayList<String>();
	private List<String> tokensOut = new ArrayList<String>();
	private int indexKeyIn;
	private int indexKeyOut;
	private boolean printMatrix;
	
	public CliOptions() {
		options.addOption("fin", true, "Input data file name");
		options.addOption("fout", true, "Output results file name");
		options.addOption("fquery", true, "Query file name");
		options.addOption("lin", true, "Length of input subsequence");
		options.addOption("lout", true, "Length of output subsequence");
		options.addOption("tokensin", true, "Token to match in data");
		options.addOption("tokensout", true, "Token to match in query");
		options.addOption("idxin", true, "Index of date column in input file");
		options.addOption("idxq", true, "Index of date column in query file");
		options.addOption("matrix", false, "Print matrix of results");
	}
	
	public void parse(String[] args) {
		try {
			cmd = parser.parse(options, args);
			
			//fin parsing
			if (cmd.hasOption("fin")) {
				fileIn = cmd.getOptionValue("fin");
			}
			else {
				throw new FileInException();
			}
			
			//fout parsing
			if (cmd.hasOption("fout")) {
				fileOut = Optional.of(cmd.getOptionValue("fout"));
			}
			
			//fquery parsing
			if (cmd.hasOption("fquery")) {
				fileQuery = Optional.of(cmd.getOptionValue("fquery"));
			}
			
			//lin parsing
			if (cmd.hasOption("fquery")) {
				lenSeqIn = countLines(fileQuery.get());
			}
			else if (cmd.hasOption("lin")) {
				lenSeqIn = Integer.parseInt(cmd.getOptionValue("lin"));
			}
			else {
				throw new LengthInException();
			}
			
			//lout parsing
			if (cmd.hasOption("lout")) {
				lenSeqOut = Integer.parseInt(cmd.getOptionValue("lout"));
			}
			else {
				lenSeqOut = lenSeqIn;
			}
			
			//tokensin parsing
			if (cmd.hasOption("tokensin")) {
				tokensIn.addAll(Arrays.asList( cmd.getOptionValue("tokensin").split(",") ));
			}
			
			//tokensout parsing
			if (cmd.hasOption("tokensout")) {
				tokensIn.addAll(Arrays.asList( cmd.getOptionValue("tokensout").split(",") ));
			}
			
			//idxin parsing
			indexKeyIn = cmd.hasOption("idxin") ? Integer.parseInt(cmd.getOptionValue("idxin")) : 0;
			
			//idxout parsing
			indexKeyOut = cmd.hasOption("idxq") ? Integer.parseInt(cmd.getOptionValue("idxq")) : 0;
			
			//matrix parsing
			printMatrix = cmd.hasOption("matrix");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (FileInException e) {
			System.out.println("-fin <String> option required");
			System.exit(-1);
		} catch (LengthInException e) {
			System.out.println("-lin <int> option required");
			System.exit(-1);
		}
	}
	
	public String getFileIn() {
		return fileIn;
	}
	
	public Optional<String> getFileOut() {
		return fileOut;
	}
	
	public Optional<String> getFileQuery() {
		return fileQuery;
	}
	
	public int getLenSeqIn() {
		return lenSeqIn;
	}
	
	public int getLenSeqOut() {
		return lenSeqOut;
	}
	
	public List<String> getTokensIn() {
		return tokensIn;
	}
	
	public List<String> getTokensOut() {
		return tokensOut;
	}
	
	public int getIndexKeyIn() {
		return indexKeyIn;
	}
	
	public int getIndexKeyOut() {
		return indexKeyOut;
	}
	
	public boolean getPrintMatrix() {
		return printMatrix;
	}
	
	private int countLines(String filename) {
		int lines = 0;
		
		try {
			File file = new File(filename);
			LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(file));
			lineNumberReader.skip(Long.MAX_VALUE);
			lines = lineNumberReader.getLineNumber();
			lineNumberReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException Occured" + e.getMessage());
		} catch (IOException e) {
			System.out.println("IOException Occured" + e.getMessage());
		}

		return lines;
	}
}
