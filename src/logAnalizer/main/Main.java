package logAnalizer.main;

import java.util.Random;

import logAnalizer._di.ModuleInjector;
import logAnalizer.dtw.DTW;
import logAnalizer.dtw.DTWFactory;
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
		
		System.out.print("Reading " + System.getProperty("user.dir") + csvFileName + " ... ");
		TimeSeries timeSeries = tokenReader.read(csvFileName, 2);
		System.out.println("done.");
		
		System.out.println(" -> Time intervals count: " + timeSeries.size());
		
		System.out.println(" -> Scan:");
		timeSeries.scan();
		
		int sizeAll = timeSeries.size();
		int sizeSegment = 24;
		
		/*System.out.println(" -> Global tokens count: " + sizeAll);
		timeSeries.unify().forEach((key,value)->{
			System.out.println("\t" + key + ": " + value);
		});*/
		
		System.out.println("Performing DTW test on random subsequances...");
		System.out.println("Subseries size: " + sizeSegment);
		
		for(int i = 0; ; i++) {
			Random rand = new Random();
			int a = rand.nextInt(sizeAll - sizeSegment);
			
			TimeSeries t1 = timeSeries.getSegment(a, a + sizeSegment);
			
/*			System.out.println(" -> t1 tokens count: " + t1.size());
			t1.unify().forEach((key,value)->{
				System.out.println("\t" + key + ": " + value);
			});
*/			
			double minDist = Double.POSITIVE_INFINITY;
			int b = 0;
			
			for(int j = 0; j < sizeAll-sizeSegment - 1; j++) {
				if ((j < a - sizeSegment) || (j > a + sizeSegment)) {
					TimeSeries t2 = timeSeries.getSegment(j, j + sizeSegment);
					double dist = dtw.DTWDistance(t1, t2);
					
					if (dist < minDist) {
						minDist = dist;
						b = j;
					}
				}
			}
			
			System.out.println("" + i + ") TestIndex = " + a + " Distance = "
					+ minDist + " - SolutionIndex = " + b);
			System.out.println("" + i + ") DateTest = " + timeSeries.getKey(a)
					+ " - DateSolution = " + timeSeries.getKey(b));
			System.out.println();
		}
	}
}
