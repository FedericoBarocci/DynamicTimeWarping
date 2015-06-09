package logAnalizer.timeSeries;

import java.util.HashSet;
import java.util.Set;

import logAnalizer.token.map.TokenMap;

public enum Distances {

	INSTANCE;
	
	private Double distance;
	
	public Double manatthanDistance(TokenMap from, TokenMap to) {
		Set<String> keys = mergeKeys(from, to);
		distance = 0.0;
		
		keys.forEach((key) -> {
			int a = from.get(key);
			int b = to.get(key);
			
			distance += Math.abs(a - b);
		});
		
		return distance;
	}
	
	public Double euclideanDistance(TokenMap from, TokenMap to) {
		Set<String> keys = mergeKeys(from, to);
		distance = 0.0;
		
		keys.forEach((key) -> {
			int a = from.get(key);
			int b = to.get(key);
			
			distance += Math.pow(a - b, 2);
		});
		
		distance = Math.sqrt(distance);
		
		return distance;
	}
	
	private Set<String> mergeKeys(TokenMap a, TokenMap b) {
		Set<String> result = new HashSet<String>();
		
		result.addAll(a.keySet());
		result.addAll(b.keySet());
		
		return result;
	}
}
