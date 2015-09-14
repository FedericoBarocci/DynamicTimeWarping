package logAnalyzer.timeSeries.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import logAnalyzer.printer.Printer;
import logAnalyzer.timeSeries.TimeSeries;
import logAnalyzer.timeSeries.TimeSeriesFactory;

public class TimeSeriesReader {
	
	private final TimeSeriesFactory timeSeriesFactory;
	
	@Inject
	public TimeSeriesReader(TimeSeriesFactory timeSeriesFactory) {
		this.timeSeriesFactory = timeSeriesFactory;
	}

	public TimeSeries read(String path, int key, String csvSplitBy) {
		Printer.get().print("Reading " + System.getProperty("user.dir") + path + " ... ");

		BufferedReader bufferedReader;
		TimeSeries timeSeries = timeSeriesFactory.build();
		List<String> values = new ArrayList<String>();
		String line = "";
		
		try {
			bufferedReader = new BufferedReader(new FileReader(path));

			/*Scan all time series from given file and initialize data structures*/
			while ((line = bufferedReader.readLine()) != null) {
				values.addAll(Arrays.asList(line.split(csvSplitBy)));
				timeSeries.add(values, key);
				values.clear();
			}

			bufferedReader.close();
		} catch (IOException | ParseException e) {
			Printer.get().printlnErr(e.getLocalizedMessage());
		}

		Printer.get().println("done.");
		
		return timeSeries;
	}
	
}
