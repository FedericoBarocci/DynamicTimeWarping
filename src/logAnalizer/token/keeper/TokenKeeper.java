package logAnalizer.token.keeper;

import java.util.List;

import logAnalizer.token.map.TokenMap;

public class TokenKeeper {

	//private NavigableMap<String, Integer> tokenMap = new TreeMap<String, Integer>();
	private TokenMap tokenMap = new TokenMap();
	
	public TokenKeeper(List<String> elements) {
		add(elements);
	}

	public void add(List<String> tokens) {
		tokens.forEach(key->{
			tokenMap.add(key);
		});
	}
	
	public void add(TokenMap inputTokenMap) {
		inputTokenMap.get().forEach((key, value)->{
			tokenMap.add(key, value);
		});
	}
	
	public TokenMap getTokenMap() {
		return tokenMap;
	}
}
