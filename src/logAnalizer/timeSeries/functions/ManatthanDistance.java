package logAnalizer.timeSeries.functions;

import java.util.Set;

import logAnalizer.token.map.TokenMap;

public class ManatthanDistance implements DistanceFunction {

	private Double distance;
	
	@Override
	public double distance(Set<String> keys, TokenMap from, TokenMap to) {
		distance = 0.0;
		
		keys.forEach((key) -> {
			int a = from.get(key);
			int b = to.get(key);
			
			distance += Math.abs(a - b);
		});
		
		return distance;
	}

}
