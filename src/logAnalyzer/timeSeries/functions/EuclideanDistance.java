package logAnalyzer.timeSeries.functions;

import java.util.Map;
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

	@Override
	public double distance(Map<String, String> keys, TokenMap from, TokenMap to) {
		distance = 0.0;
		
		keys.forEach((x, y) -> {
			int a = from.get(x);
			int b = to.get(y);
			
			distance += Math.abs(a - b);
		});
		
		return distance;
	}

}
