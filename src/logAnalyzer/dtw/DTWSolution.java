package logAnalyzer.dtw;

import java.util.Date;

public class DTWSolution {
	
	private final int index;
	private final double distance;
	private final Date date;
	
	public DTWSolution(int index, double distance, Date date) {
		this.index = index;
		this.distance = distance;
		this.date = date;
	}
	
	public int getIndex() {
		return index;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public Date getDate() {
		return date;
	}
	
	public String toString() {
		return "index: " + getIndex() + " distance: " + getDistance()
				+ " date: " + getDate();
	}
}
