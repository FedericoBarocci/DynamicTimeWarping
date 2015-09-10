package logAnalyzer.dtw;

import javax.inject.Inject;

import logAnalyzer.configuration.Configuration;
import logAnalyzer.printer.Printer;
import logAnalyzer.timeSeries.Distances;
import logAnalyzer.timeSeries.TimeSeries;

public class DTW {
	
	private final Configuration configuration;

	@Inject
	public DTW(Configuration configuration) {
		this.configuration = configuration;
	}
	
	public DTWSolution findSolution(Distances functor, TimeSeries db, TimeSeries query,
			 int indexDB, /*int lenQuery,*/ int lenMatch) {
		double minDistance = Double.POSITIVE_INFINITY;
		int indexSolution = 0;
		
		//Check for all possible subsequences
		for(int i = 0; i < db.size() - lenMatch - 1; i++) {
			
			//Avoid testing queries starting in original DB sub-sequence.
			//This condition prevents auto-similarity, hence not relevant results.
			
			//XXX: Maybe, can be improved this condition?
			//XXX: Is it relevant find correlation between partially overlapping sub-sequences?
			if ((i >= indexDB - query.size()) && (i <= indexDB + query.size())) {
				continue;
			}
			
			TimeSeries test = db.getSegment(i, i + lenMatch);
			double localDistance = calculate(functor, query, test);
			
			if (localDistance < minDistance) {
				minDistance = localDistance;
				indexSolution = i;
			}
		}
		
		if (configuration.isPrintMatrix()) {
			printMatrix(functor, query, db.getSegment(indexSolution, indexSolution + lenMatch));
		}
		
		return new DTWSolution(indexSolution, minDistance, db.getKey(indexSolution));
	}


	public DTWSolution findSolution(Distances functor, TimeSeries db, TimeSeries query) {
		double minDistance = Double.POSITIVE_INFINITY;
		int indexSolution = 0;
		
		//Check for all possible subsequences
		for(int i = 0; i < db.size() - query.size() - 1; i++) {
			TimeSeries test = db.getSegment(i, i + query.size());
			double localDistance = calculate(functor, query, test);
			
			if (localDistance < minDistance) {
				minDistance = localDistance;
				indexSolution = i;
			}
		}

		if (configuration.isPrintMatrix()) {
			printMatrix(functor, query, db.getSegment(indexSolution, indexSolution + query.size()));
		}
		
		return new DTWSolution(indexSolution, minDistance, db.getKey(indexSolution));
	}

	public double calculate(Distances functor, TimeSeries t1, TimeSeries t2) {
		int i, j;
		int n = t1.size();
		int m = t2.size();
		DTWMatrix matrix = new DTWMatrix(n, m);
		
		for(i = 0; i < n; i++) {
			for(j = 0; j < m; j++) {
				double cost = functor.calculate(t1.getIndex(i), t2.getIndex(j));
				double m1 = Math.min(matrix.get(i-1, j), matrix.get(i, j-1));
				matrix.set(i, j, cost + Math.min(m1, matrix.get(i-1, j-1)));
			}
		}
		
		return matrix.get(n-1, m-1);
	}

	private void printMatrix(Distances functor, TimeSeries t1, TimeSeries t2) {
		int i, j;
		int n = t1.size();
		int m = t2.size();
		DTWMatrix matrix = new DTWMatrix(n, m);
		
		System.out.println("N = " + n + " - M = " + m);
		
		for(i = 0; i < n; i++) {
			for(j = 0; j < m; j++) {
				double cost = functor.calculate(t1.getIndex(i), t2.getIndex(j));
				double m1 = Math.min(matrix.get(i-1, j), matrix.get(i, j-1));
				matrix.set(i, j, cost + Math.min(m1, matrix.get(i-1, j-1)));
			}
		}
		
		for(i = 0; i < n; i++) {
			for(j = 0; j < m; j++) {
				Printer.get().print("" + matrix.get(i, j) + "\t");
			}
			
			Printer.get().println();
		}
	}
	
}
