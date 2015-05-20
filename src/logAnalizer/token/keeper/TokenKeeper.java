package logAnalizer.token.keeper;

import java.util.List;

import logAnalizer.token.map.TokenMap;

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
	
	public TokenMap merge(TokenMap map) {
		TokenMap result = new TokenMap();
		
		result.putAll(tokenMap);
		result.putAll(map);
		
		return result;
	}
}
