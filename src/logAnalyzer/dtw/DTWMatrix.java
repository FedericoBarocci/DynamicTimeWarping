package logAnalyzer.dtw;

import logAnalyzer.printer.Printer;

public class DTWMatrix implements Cloneable {

	private Double[][] matrix;
	private int n;
	private int m;
	
	public DTWMatrix(int n, int m) {
		this.n = n;
		this.m = m;
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
	
	public Double getSolution() {
		return matrix[n-1][m-1];
	}

	public void print() {
		int i, j;
		
		Printer.get().println();
		//Printer.get().println("N = " + n + " - M = " + m);
		
		for(i = 0; i < n; i++) {
			for(j = 0; j < m; j++) {
				Printer.get().print("" + get(i, j) + "\t");
			}
			
			Printer.get().println();
		}
		
		Printer.get().println();
	}

	public DTWMatrix clone() {
		try {
			return (DTWMatrix) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}
}
