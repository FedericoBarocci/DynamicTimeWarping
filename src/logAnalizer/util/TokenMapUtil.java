package logAnalizer.util;

import logAnalizer.token.map.TokenMap;

public enum TokenMapUtil {

	INSTANCE;
	
	private Double distance;
	
	public Double manatthanDistance(TokenMap from, TokenMap to) {
		distance = 0.0;
		
		from.forEach((key, value)->{
			distance += Math.abs(value - to.get(key));
		});
		
		return distance;
	}
	
	public Double euclideanDistance(TokenMap from, TokenMap to) {
		distance = 0.0;
		
		from.forEach((key, value)->{
			distance += Math.pow(value - to.get(key), 2);
		});
		
		distance = Math.sqrt(distance);
		
		return distance;
	}
}
