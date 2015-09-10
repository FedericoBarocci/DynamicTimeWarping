package logAnalyzer.timeSeries.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import logAnalyzer.dtw.DTW;
import logAnalyzer.dtw.DTWSolution;
import logAnalyzer.printer.Printer;
import logAnalyzer.timeSeries.Distances;
import logAnalyzer.timeSeries.TimeSeries;
import logAnalyzer.timeSeries.TimeSeriesFactory;

public class TimeSeriesService {
	
	private final TimeSeriesFactory timeSeriesFactory;
	private final DTW dtw;

	@Inject
	public TimeSeriesService(TimeSeriesFactory timeSeriesFactory, DTW dtw) {
		this.timeSeriesFactory = timeSeriesFactory;
		this.dtw = dtw;
	}

	public TimeSeries read(String path, int key, String csvSplitBy) throws IOException, ParseException {
		Printer.get().print("Reading " + System.getProperty("user.dir") + path + " ... ");

		BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
		TimeSeries timeSeries = timeSeriesFactory.build();
		List<String> values = new ArrayList<String>();
		String line = "";
		
		while ((line = bufferedReader.readLine()) != null) {
			values.addAll(Arrays.asList(line.split(csvSplitBy)));
			timeSeries.add(values, key);
			values.clear();
		}
		
		bufferedReader.close();

		Printer.get().println("done.");
		
		return timeSeries;
	}
	
	public void analyze(TimeSeries db, int lenQuery, int lenMatch) {
		Printer.get().println("Performing DTW test on random subsequences...");
		Printer.get().println("Subsequence size: " + lenQuery);
		Printer.get().println("Matching size: " + lenMatch);

		int i = 0;
		Random rand = new Random();
		
		while (true) {
			int indexdb = rand.nextInt(db.size() - lenQuery);

			TimeSeries query = db.getSegment(indexdb, indexdb + lenQuery);
			Printer.get().println("" + i + ". TestIndex = " + indexdb + " DateTest = " + db.getKey(indexdb));
			
			DTWSolution e = dtw.findSolution(Distances.EUCLIDEAN, db, query, indexdb, /*lenQuery,*/ lenMatch);
			Printer.get().println("" + i + ". EUCLIDEAN DISTANCE. " + e);
			
			DTWSolution m = dtw.findSolution(Distances.MANATTHAN, db, query, indexdb, /*lenQuery,*/ lenMatch);
			Printer.get().println("" + i + ". MANATTHAN DISTANCE. " + m);
			
			Printer.get().println();
			i++;
		}
	}

	public void analyze(TimeSeries db, TimeSeries query) {
		Printer.get().println("Performing DTW test on 2 input sequences...");
		
		DTWSolution e = dtw.findSolution(Distances.EUCLIDEAN, db, query);
		DTWSolution m = dtw.findSolution(Distances.MANATTHAN, db, query);

		Printer.get().println("EUCLIDEAN DISTANCE. " + e);
		Printer.get().println("MANATTHAN DISTANCE. " + m);
		Printer.get().println();
	}
}
