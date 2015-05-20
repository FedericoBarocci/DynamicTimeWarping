package logAnalizer.token.map;

import java.util.Map;
import java.util.TreeMap;

public class TokenMap extends TreeMap<String, Integer> {

	private static final long serialVersionUID = 6919333871411604844L;

	@Override
	public Integer put(String key, Integer value) {
		String id = key.trim();
		Integer update = value + get(id);
		
		super.put(id, update);
		
		return update;
	}
	
	public Integer put(String key) {
		return put(key, 1);
	}
	
	@Override
	public Integer get(Object key) {
		Integer result = 0;
		
		if(containsKey(key)) {
			result = super.get(key);
		}
		
		return result;
	}
	
	@Override
	public void putAll(Map<? extends String, ? extends Integer> map) {
		map.forEach((key, value) -> {
			put(key, value);
		});
	}
	
	public void empty() {
		this.values().forEach(c->{
			c = 0;
		});
	}
}
