package tirocinio.token;

import java.util.ArrayList;
import java.util.List;

public class Tokens {

	private List<String[]> tokens = new ArrayList<>();

	public List<String[]> getTokens() {
		return tokens;
	}
	
	public void add(String[] strings) {
		tokens.add(strings);
	}
}
