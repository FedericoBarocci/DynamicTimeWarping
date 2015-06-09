package logAnalizer.timeSeries;

import java.util.HashSet;
import java.util.Set;

import logAnalizer.timeSeries.functions.IDistanceFunction;
import logAnalizer.timeSeries.functions.EuclideanDistance;
import logAnalizer.timeSeries.functions.ManatthanDistance;
import logAnalizer.token.map.TokenMap;

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
	
	private Set<String> mergeKeys(TokenMap a, TokenMap b) {
		Set<String> result = new HashSet<String>();
		
		result.addAll(a.keySet());
		result.addAll(b.keySet());
		
		return result;
	}

}
