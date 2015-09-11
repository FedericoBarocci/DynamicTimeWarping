package logAnalyzer.dtw.clioptions;

import javax.inject.Inject;

import logAnalyzer.configuration.Configuration;
import logAnalyzer.printer.Printer;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
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
		//for(String s : args) System.out.println(s);
		try {
			//Parsing command line args
			DefaultParser defaultParser = new DefaultParser();
			CommandLine commandLine = defaultParser.parse(options, args);
			
			//[debug] Show parsed input
			for (Option input : commandLine.getOptions()) {
				System.out.println(input.getOpt() + " -> " + input.getValue());
			}
			
			//Configuration step
			for (Option input : commandLine.getOptions()) {
				optionMap.get(input).ifPresent(optEnum->{
					optEnum.configure(configuration, input.getValues());
				});
			}
			
			//Fix bad values for subsequences's length
			configuration.validate();
			
			//[debug] Show defined configuration
			System.out.println("Configuration DONE!");
			configuration.showConfigStatus();
		} catch (ParseException e) {
			Printer.get().printlnErr(e.toString());
		}
		
		//Fix bad values for subsequences's length
		return configuration;
	}
}
