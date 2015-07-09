package logAnalyzer.main;

import java.util.Random;

import logAnalyzer._di.ModuleInjector;
import logAnalyzer.dtw.DTW;
import logAnalyzer.dtw.DTWFactory;
import logAnalyzer.dtw.DTWSolution;
import logAnalyzer.dtw.options.CliOptions;
import logAnalyzer.timeSeries.Distances;
import logAnalyzer.timeSeries.TimeSeries;
import logAnalyzer.token.reader.TokenReader;
import logAnalyzer.token.reader.TokenReaderFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {
	
	private final CliOptions cliOptions;

	public Main(CliOptions cliOptions) {
		this.cliOptions = cliOptions;
	}
	
	public static void main(String[] args) {
		
		if (args.length != 3) {
			System.err.println("Usage: [Filename] [indexKey] [sizeSegment]");
			System.exit(-1);
		}
		
		String csvFileName = args[0];
		int indexKey = Integer.parseInt(args[1]);
		int sizeSegment = Integer.parseInt(args[2]);

		Injector injector = Guice.createInjector(new ModuleInjector());
		
		TokenReaderFactory tokenReaderFactory = injector.getInstance(TokenReaderFactory.class);
		TokenReader tokenReader = tokenReaderFactory.create();

		DTWFactory dtwFactory = injector.getInstance(DTWFactory.class);
		DTW dtw = dtwFactory.create();

		System.out.print("Reading " + System.getProperty("user.dir")
				+ csvFileName + " ... ");
		TimeSeries timeSeries = tokenReader.read(csvFileName, indexKey, ";");
		System.out.println("done.");

		System.out.println(" -> Time intervals count: " + timeSeries.size());

		System.out.println(" -> Scan:");
		timeSeries.scan();

		int sizeAll = timeSeries.size();
		

		System.out.println("Performing DTW test on random subsequences...");
		System.out.println("Subsequence size: " + sizeSegment);

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
