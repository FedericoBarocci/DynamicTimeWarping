package logAnalizer.dtw;

public class DTWMatrix {

	private Double[][] matrix;
	
	public DTWMatrix(int n, int m) {
		matrix = new Double[n][m];
	}
	
	public Double get(int i, int j) {
		if (i < 0 || j < 0) {
			if (i == j) {
				return 0.0;
			}
			
			return Double.POSITIVE_INFINITY;
		}
		
		return matrix[i][j];
	}
	
	public void set(int i, int j, Double value) {
		matrix[i][j] = value;
	}
}
