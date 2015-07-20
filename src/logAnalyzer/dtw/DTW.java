package logAnalyzer.dtw;

import javax.inject.Inject;

import logAnalyzer.timeSeries.Distances;
import logAnalyzer.timeSeries.TimeSeries;

public class DTW {
	
	@Inject
	public DTW(){}
	
	public DTWSolution findSolution(Distances functor, TimeSeries db, TimeSeries query,
			 int indexQuery, int sizeSolution) {
		double minDistance = Double.POSITIVE_INFINITY;
		int indexSolution = 0;
		
		for(int i = 0; i < db.size() - sizeSolution - 1; i++) {
			if ((i < indexQuery - sizeSolution) || (i > indexQuery + sizeSolution)) {
				TimeSeries test = db.getSegment(i, i + sizeSolution);
				double localDistance = calculate(functor, query, test);
				
				if (localDistance < minDistance) {
					minDistance = localDistance;
					indexSolution = i;
				}
			}
		}
		
		return new DTWSolution(indexSolution, minDistance, db.getKey(indexSolution));
	}
	
	public DTWSolution findSolution(Distances functor, TimeSeries db, TimeSeries query) {
		double minDistance = Double.POSITIVE_INFINITY;
		int indexSolution = 0;
		
		for(int i = 0; i < db.size() - query.size() - 1; i++) {
			TimeSeries test = db.getSegment(i, i + query.size());
			double localDistance = calculate(functor, query, test);
			
			if (localDistance < minDistance) {
				minDistance = localDistance;
				indexSolution = i;
			}
		}
		
		return new DTWSolution(indexSolution, minDistance, db.getKey(indexSolution));
	}

	public double calculate(Distances distances, TimeSeries t1, TimeSeries t2) {
		int i, j;
		int n = t1.size();
		int m = t2.size();
		DTWMatrix matrix = new DTWMatrix(n, m);
		
		for(i = 0; i < n; i++) {
			for(j = 0; j < m; j++) {
				double cost = distances.calculate(t1.getIndex(i), t2.getIndex(j));
				double m1 = Math.min(matrix.get(i-1, j), matrix.get(i, j-1));
				matrix.set(i, j, cost + Math.min(m1, matrix.get(i-1, j-1)));
			}
		}
		
		return matrix.get(n-1, m-1);
	}
	
}
