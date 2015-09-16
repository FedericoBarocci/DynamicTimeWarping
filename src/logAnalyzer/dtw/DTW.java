package logAnalyzer.dtw;

import javax.inject.Inject;

import logAnalyzer.configuration.Configuration;
import logAnalyzer.timeSeries.TimeSeries;
import logAnalyzer.timeSeries.functions.Distances;

public class DTW {
	
	private final Configuration configuration;

	@Inject
	public DTW(Configuration configuration) {
		this.configuration = configuration;
	}
	
	/*Test query extracted from the database*/
	public DTWSolution findSolution(Distances functor, TimeSeries db, TimeSeries query, int indexDB, int lenMatch) {
		double minDistance = Double.POSITIVE_INFINITY;
		int indexSolution = 0;
		DTWMatrix solutionMatrix = new DTWMatrix(query.size(), lenMatch-1);
		
		//Check for all possible subsequences
		for(int i = 0; i < db.size() - lenMatch - 1; i++) {
			
			//Avoid testing queries near original DB sub-sequence.
			//This condition prevents auto-similarity, hence not relevant results.
			
			//XXX: Maybe, can be improved this condition?
			//XXX: Is it relevant find correlation between partially overlapping sub-sequences?
			if ((i >= indexDB - (query.size()/2)) && (i <= indexDB + (query.size()/2))) {
				continue;
			}
			
			TimeSeries test = db.getSegment(i, i + lenMatch-1);
			DTWMatrix testMatrix = calculate(functor, query, test);
			double localDistance = testMatrix.getSolution();
			
			if (localDistance < minDistance) {
				minDistance = localDistance;
				indexSolution = i;
				solutionMatrix = testMatrix.clone();
			}
		}
		
		if (configuration.isPrintMatrix()) {
			solutionMatrix.print();
		}
		
		return new DTWSolution(indexSolution, minDistance, db.getKey(indexSolution));
	}

	/*Test user provided query*/
	public DTWSolution findSolution(Distances functor, TimeSeries db, TimeSeries query, int lenMatch) {
		double minDistance = Double.POSITIVE_INFINITY;
		int indexSolution = 0;
		DTWMatrix solutionMatrix = new DTWMatrix(query.size(), lenMatch-1);
		
		//Check for all possible subsequences
		for(int i = 0; i < db.size() - lenMatch - 1; i++) {
			TimeSeries test = db.getSegment(i, i + lenMatch-1);
			DTWMatrix testMatrix = calculate(functor, query, test);
			double localDistance = testMatrix.getSolution();
			
			if (localDistance < minDistance) {
				minDistance = localDistance;
				indexSolution = i;
				solutionMatrix = testMatrix.clone();
			}
		}

		if (configuration.isPrintMatrix()) {
			solutionMatrix.print();
		}
		
		return new DTWSolution(indexSolution, minDistance, db.getKey(indexSolution));
	}

	/*Perform effective DTW algorithm between two time series*/
	private DTWMatrix calculate(Distances functor, TimeSeries query, TimeSeries sequence) {
		int i, j;
		int n = query.size();
		int m = sequence.size();
		DTWMatrix matrix = new DTWMatrix(n, m);
		
		/*Simple implementation of DTW algorithm*/
		/*This could be optimized. See: FastDTW, SparseDTW, LB_Keogh, LB_Improved*/
		for(i = 0; i < n; i++) {
			for(j = 0; j < m; j++) {
				double cost = functor.calculate(query.getIndex(i, configuration.getTokensQuery()), sequence.getIndex(j, configuration.getTokensMatch()));
				double m1 = Math.min(matrix.get(i-1, j), matrix.get(i, j-1));
				double m2 = Math.min(m1, matrix.get(i-1, j-1));
				matrix.set(i, j, cost + m2);
			}
		}
		
		return matrix;
	}
}
