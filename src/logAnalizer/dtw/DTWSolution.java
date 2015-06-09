package logAnalizer.dtw;

public class DTWSolution {
	private int index;
	private double distance;
	
	public DTWSolution(int index, double distance) {
		this.index = index;
		this.distance = distance;
	}
	
	public int getIndex() {
		return index;
	}
	
	public double getDistance() {
		return distance;
	}
}
