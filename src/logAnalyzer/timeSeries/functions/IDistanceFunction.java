package logAnalyzer.timeSeries.functions;

import java.util.Map;
import java.util.Set;

import logAnalyzer.token.map.TokenMap;

public interface IDistanceFunction {
	double distance(Set<String> keys, TokenMap from, TokenMap to);
	double distance(Map<String, String> keys, TokenMap from, TokenMap to);
}
