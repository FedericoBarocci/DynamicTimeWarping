package logAnalizer.timeSeries.functions;

import java.util.Set;

import logAnalizer.token.map.TokenMap;

public interface DistanceFunction {
	double distance(Set<String> keys, TokenMap from, TokenMap to);
}
