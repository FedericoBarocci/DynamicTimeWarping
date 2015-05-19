package logAnalizer.token.map;

import java.util.NavigableMap;
import java.util.TreeMap;

public class TokenMap {

	private NavigableMap<String, Integer> tokenMap = new TreeMap<String, Integer>();

	public void add(String key, Integer value) {
		String id = key.trim();
		
		if(tokenMap.containsKey(id)) {
			Integer count = tokenMap.get(id);
			count += value;
			tokenMap.put(id, count);
		}
		else {
			tokenMap.put(id, value);
		}
	}
	
	public void add(String key) {
		add(key, 1);
	}
	
	public NavigableMap<String, Integer> get() {
		return tokenMap;
	}
	
	public void scan() {
		tokenMap.forEach((key,value)->{
			System.out.println("\t" + key + ": " + value);
		});
	}
}
