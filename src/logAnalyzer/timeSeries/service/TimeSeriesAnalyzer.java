package logAnalyzer.timeSeries.service;

import java.util.Random;

import javax.inject.Inject;

import logAnalyzer.dtw.DTW;
import logAnalyzer.dtw.DTWSolution;
import logAnalyzer.printer.Printer;
import logAnalyzer.timeSeries.Distances;
import logAnalyzer.timeSeries.TimeSeries;

public class TimeSeriesAnalyzer {
	
	private final DTW dtw;
	
	@Inject
	public TimeSeriesAnalyzer(DTW dtw) {
		this.dtw = dtw;
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
