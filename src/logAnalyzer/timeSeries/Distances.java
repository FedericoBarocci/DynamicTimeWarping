package logAnalyzer.timeSeries;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import logAnalyzer.timeSeries.functions.EuclideanDistance;
import logAnalyzer.timeSeries.functions.IDistanceFunction;
import logAnalyzer.timeSeries.functions.ManatthanDistance;
import logAnalyzer.token.map.TokenMap;

public enum Distances {

	EUCLIDEAN(new EuclideanDistance()),
	MANATTHAN(new ManatthanDistance());
	
	private final IDistanceFunction distanceFunction;
	
	Distances(IDistanceFunction distanceFunction) {
		this.distanceFunction = distanceFunction;
	}
	
	public double calculate(TokenMap from, TokenMap to) {
		return distanceFunction.distance(mergeKeys(from, to), from, to);
	}
	
	public double calculate(Map<String, String> keys, TokenMap from, TokenMap to) {
		return distanceFunction.distance(keys, from, to);
	}
	
	private Set<String> mergeKeys(TokenMap a, TokenMap b) {
		Set<String> result = new HashSet<String>();
		
		result.addAll(a.keySet());
		result.addAll(b.keySet());
		
		return result;
	}

}
