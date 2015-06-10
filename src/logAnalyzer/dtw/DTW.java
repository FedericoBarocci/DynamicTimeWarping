package logAnalyzer.dtw;

import logAnalyzer.timeSeries.Distances;
import logAnalyzer.timeSeries.TimeSeries;

public class DTW {
	
	private Double calculateDistance(Distances distances, TimeSeries s, TimeSeries t) {
		int i, j;
		int n = s.size();
		int m = t.size();
		DTWMatrix matrix = new DTWMatrix(n, m);
		
		for(i = 0; i < n; i++) {
			for(j = 0; j < m; j++) {
				double cost = distances.calculate(s.getIndex(i), t.getIndex(j));
				double m1 = Math.min(matrix.get(i-1, j), matrix.get(i, j-1));
				matrix.set(i, j, cost + Math.min(m1, matrix.get(i-1, j-1)));
			}
		}
		
		return matrix.get(n-1, m-1);
	}
	
	public DTWSolution findSolution(Distances distances, TimeSeries orig, TimeSeries all, int indexOrig, int sizeSolution) {
		double minDistance = Double.POSITIVE_INFINITY;
		int indexSolution = 0;
		
		for(int i = 0; i < all.size() - sizeSolution - 1; i++) {
			if ((i < indexOrig - sizeSolution) || (i > indexOrig + sizeSolution)) {
				TimeSeries test = all.getSegment(i, i + sizeSolution);
				double localDistance = calculateDistance(distances, orig, test);
				
				if (localDistance < minDistance) {
					minDistance = localDistance;
					indexSolution = i;
				}
			}
		}
		
		return new DTWSolution(indexSolution, minDistance);
	}

}
