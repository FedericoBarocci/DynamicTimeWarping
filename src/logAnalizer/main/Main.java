package logAnalizer.main;

import java.util.Random;

import logAnalizer._di.ModuleInjector;
import logAnalizer.dtw.DTW;
import logAnalizer.dtw.DTWFactory;
import logAnalizer.dtw.DTWSolution;
import logAnalizer.timeSeries.Distances;
import logAnalizer.timeSeries.TimeSeries;
import logAnalizer.token.reader.TokenReader;
import logAnalizer.token.reader.TokenReaderFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new ModuleInjector());

		String csvFileName = "data/filter_eventlog.csv";

		TokenReaderFactory tokenReaderFactory = injector.getInstance(TokenReaderFactory.class);
		TokenReader tokenReader = tokenReaderFactory.create();

		DTWFactory dtwFactory = injector.getInstance(DTWFactory.class);
		DTW dtw = dtwFactory.create();

		System.out.print("Reading " + System.getProperty("user.dir")
				+ csvFileName + " ... ");
		TimeSeries timeSeries = tokenReader.read(csvFileName, 2, ";");
		System.out.println("done.");

		System.out.println(" -> Time intervals count: " + timeSeries.size());

		System.out.println(" -> Scan:");
		timeSeries.scan();

		int sizeAll = timeSeries.size();
		int sizeSegment = 24;

		System.out.println("Performing DTW test on random subsequences...");
		System.out.println("Subseries size: " + sizeSegment);

		for (int i = 0;; i++) {
			Random rand = new Random();
			int a = rand.nextInt(sizeAll - sizeSegment);

			TimeSeries t1 = timeSeries.getSegment(a, a + sizeSegment);

			DTWSolution e = dtw.findSolution(Distances.EUCLIDEAN, t1, timeSeries, a, sizeSegment);
			DTWSolution m = dtw.findSolution(Distances.MANATTHAN, t1, timeSeries, a, sizeSegment);

			System.out.println("" + i + ") TestIndex = " + a + " DateTest = " + timeSeries.getKey(a));
			
			System.out.println("" + i + ") EUCLIDEAN DISTANCE: SolutionIndex = " + e.getIndex()
					+ " Distance = " + e.getDistance() 
					+ " DateSolution = " + timeSeries.getKey(e.getIndex()));
			
			System.out.println("" + i + ") MANATTHAN DISTANCE: SolutionIndex = " + m.getIndex()
					+ " Distance = " + m.getDistance() 
					+ " DateSolution = " + timeSeries.getKey(m.getIndex()));
			
			System.out.println();
		}
	}
}
