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
		
		//optionMap.keySet().forEach(option->{
		for(OptionValues option : OptionValues.values()){
			options.addOption(option.getOption());		
		}
	}
	
	public Configuration parse(String[] args) {
		try {
			DefaultParser defaultParser = new DefaultParser();
			CommandLine commandLine = defaultParser.parse(options, args);
			
			for (Option input : commandLine.getOptions()) {
				optionMap.get(input).ifPresent(optEnum->{
					optEnum.configure(configuration, input.getValue());
				});
			}
		} catch (ParseException e) {
			Printer.printlnErr(e.toString());
		}
		
		return configuration.validate();
	}
}
