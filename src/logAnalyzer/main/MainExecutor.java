package logAnalyzer.main;

import java.util.NoSuchElementException;

import javax.inject.Inject;

import logAnalyzer.configuration.Configuration;
import logAnalyzer.dtw.clioptions.CliOptions;
import logAnalyzer.timeSeries.TimeSeries;
import logAnalyzer.timeSeries.service.TimeSeriesAnalyzer;

public class MainExecutor implements IMainExecutor {

	private final CliOptions cliOptions;
	private final TimeSeriesAnalyzer timeSeriesAnalyzer;

	@Inject
	public MainExecutor(CliOptions cliOptions, TimeSeriesAnalyzer timeSeriesAnalyzer) {
		this.cliOptions = cliOptions;
		this.timeSeriesAnalyzer = timeSeriesAnalyzer;
	}

	@Override
	public void start(String[] args) {
		//Parsing command line options
		Configuration configuration = cliOptions.parse(args);
		TimeSeries db = configuration.getDB();
		
		if (configuration.isPrintDB()) {
			db.scan();
		}
		
		try {
			// Only with user provided file query
			TimeSeries query = configuration.getQuery().get();
			timeSeriesAnalyzer.analyze(db, query, configuration.getLenMatch());
		} catch (NoSuchElementException e) {
			// No file query, loop inner tests
			timeSeriesAnalyzer.analyze(db, configuration.getLenQuery(), configuration.getLenMatch());
		}
	}
}
