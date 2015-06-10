package logAnalyzer.timeSeries.functions;

import java.util.Set;

import logAnalyzer.token.map.TokenMap;

public class EuclideanDistance implements IDistanceFunction {

	private Double distance;
	
	@Override
	public double distance(Set<String> keys, TokenMap from, TokenMap to) {
		distance = 0.0;
		
		keys.forEach((key) -> {
			int a = from.get(key);
			int b = to.get(key);
			
			distance += Math.pow(a - b, 2);
		});
		
		distance = Math.sqrt(distance);
		
		return distance;
	}

}
