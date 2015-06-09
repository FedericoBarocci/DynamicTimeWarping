package logAnalizer.dtw;

import logAnalizer.timeSeries.Distances;
import logAnalizer.timeSeries.TimeSeries;

public class DTW {
	
	public Double DTWDistance(TimeSeries s, TimeSeries t) {
		int i, j;
		int n = s.size();
		int m = t.size();
		DTWMatrix matrix = new DTWMatrix(n, m);
		
		for(i = 0; i < n; i++) {
			for(j = 0; j < m; j++) {
				double cost = Distances.INSTANCE.euclideanDistance(s.getIndex(i), t.getIndex(j));
				double m1 = Math.min(matrix.get(i-1, j), matrix.get(i, j-1));
				matrix.set(i, j, cost + Math.min(m1, matrix.get(i-1, j-1)));
			}
		}
		
		return matrix.get(n-1, m-1);
	}

}
