package logAnalyzer.dtw.clioptions;

import javax.inject.Inject;

import logAnalyzer.configuration.Configuration;
import logAnalyzer.printer.Printer;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class CliOptions {

	private final OptionMap optionMap;
	private final Configuration configuration;
	
	private Options options = new Options();
	
	@Inject
	public CliOptions(OptionMap optionMap, Configuration configuration) {
		this.optionMap = optionMap;
		this.configuration = configuration;
		
		//Initialize command line options
		for(OptionValues option : OptionValues.values()){
			options.addOption(option.getOption());
		}
	}
	
	public Configuration parse(String[] args) {
		try {
			Printer.get().println("Starting environment...");
			
			//Parsing command line args
			
			//XXX: Apache Commons CLI bug
			// For now, use GNUParser instead of DefaultParser
			// https://issues.apache.org/jira/browse/CLI-255
			
			//DefaultParser defaultParser = new DefaultParser();
			CommandLineParser defaultParser = new GnuParser();
			CommandLine commandLine = defaultParser.parse(options, args);
			
			//Configuration step
			for (Option input : commandLine.getOptions()) {
				optionMap.get(input).ifPresent(optEnum->{
					optEnum.configure(configuration, input.getValues());
				});
			}
			
			if (configuration.isHelp()) {
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp("logAnalyzer", options);
				
				System.exit(0);
			}
			
			//Fix bad values for subsequences's length
			configuration.initialize();
			
			//[debug] Show defined configuration
			Printer.get().println("Configuration done!");
			configuration.showConfigStatus();
		} catch (ParseException e) {
			Printer.get().printlnErr(e.toString());
		}
		
		//Fix bad values for subsequences's length
		return configuration;
	}
}
