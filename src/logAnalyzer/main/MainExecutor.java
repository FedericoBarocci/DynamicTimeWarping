package logAnalyzer.main;

import java.io.IOException;
import java.text.ParseException;
import java.util.NoSuchElementException;

import javax.inject.Inject;

import logAnalyzer.configuration.Configuration;
import logAnalyzer.dtw.clioptions.CliOptions;
import logAnalyzer.printer.Printer;
import logAnalyzer.timeSeries.TimeSeries;
import logAnalyzer.timeSeries.service.TimeSeriesService;

public class MainExecutor implements IMainExecutor {

	private final CliOptions cliOptions;
	private final TimeSeriesService timeSeriesService;

	@Inject
	public MainExecutor(CliOptions cliOptions, TimeSeriesService timeSeriesService) {
		this.cliOptions = cliOptions;
		this.timeSeriesService = timeSeriesService;
	}

	@Override
	public void start(String[] args) {
		Configuration config = cliOptions.parse(args);
		
		try {
			TimeSeries db = timeSeriesService.read(config.getFileNameIn(), config.getIndexKeyIn(), ";");
			
			if (config.isPrintDB()) {
				db.scan();
			}
			
			try {
				String queryFileName = config.getFileNameQuery().get();
				TimeSeries query = timeSeriesService.read(queryFileName, config.getIndexKeyQuery(), ";");
				timeSeriesService.analyze(db, query);
			}
			catch (NoSuchElementException e) {
				timeSeriesService.analyze(db, config.getLenSeqIn());
			}
		} 
		catch (IOException | ParseException e) {
			Printer.printlnErr(e.getLocalizedMessage());
		}
	}
}
