package logAnalyzer.timeSeries.functions;

import java.util.HashSet;
import java.util.Set;

import logAnalyzer.token.map.TokenMap;

public enum Distances {

	EUCLIDEAN(new EuclideanDistance()),
	MANATTHAN(new ManhattanDistance());
	
	private final IDistanceFunction distanceFunction;
	
	Distances(IDistanceFunction distanceFunction) {
		this.distanceFunction = distanceFunction;
	}
	
	public double calculate(TokenMap from, TokenMap to) {
		return distanceFunction.distance(mergeKeys(from, to), from, to);
	}
	
	private Set<String> mergeKeys(TokenMap a, TokenMap b) {
		Set<String> result = new HashSet<String>();
		
		result.addAll(a.keySet());
		result.addAll(b.keySet());
		
		return result;
	}

}
