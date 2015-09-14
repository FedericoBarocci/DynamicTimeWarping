package logAnalyzer.token.keeper;

import java.util.List;

import logAnalyzer.token.map.TokenMap;

public class TokenKeeper {

	private TokenMap tokenMap = new TokenMap();
	
	public TokenKeeper(List<String> elements) {
		add(elements);
	}

	public void add(List<String> tokens) {
		tokens.forEach(key->{
			tokenMap.put(key);
		});
	}
	
	public void add(TokenMap map) {
		tokenMap.putAll(map);
	}
	
	public TokenMap getTokenMap() {
		return tokenMap;
	}
	
	public void scan() {
		tokenMap.forEach((key,value)->{
			System.out.println("\t" + key + ": " + value);
		});
	}
}
