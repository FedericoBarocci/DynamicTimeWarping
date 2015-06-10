package logAnalyzer.token.keeper;

import java.util.Collections;
import java.util.List;

import javax.inject.Singleton;

@Singleton
public class TokenKeeperFactory {

	public static TokenKeeper create(List<String> elements) {
		return new TokenKeeper(elements);
	}

	public static TokenKeeper create() {
		return new TokenKeeper(Collections.emptyList());
	}
}
