package logAnalizer.token;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

import logAnalizer.token.keeper.TokenKeeper;
import logAnalizer.token.keeper.TokenKeeperFactory;

import org.apache.commons.lang3.time.DateUtils;

public class Tokens {

	private final DateFormat target = new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss.SSSSSS");

	private NavigableMap<Date, TokenKeeper> tokens = new TreeMap<Date, TokenKeeper>();

	public TokenKeeper unify() {
		TokenKeeper app = TokenKeeperFactory.create();
		
		tokens.values().forEach(c->{
			app.add(c.getTokenMap());
		});
		
		return app;
	}
	
	public void add(List<String> elements, int key) {
		Date dateKey;
		
		try {
			Date date = target.parse(elements.get(key));
			dateKey = DateUtils.truncate(date, Calendar.HOUR);
			
			elements.remove(key);
			
			if(tokens.containsKey(dateKey)) {
				tokens.get(dateKey).add(elements);
			}
			else {
				tokens.put(dateKey, TokenKeeperFactory.create(elements));
			}

			//tokens.put(date, values);
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public int size() {
		return tokens.size();
	}
	
	public void scan() {
		tokens.forEach((key, value)->{
			System.out.println(key);
			value.getTokenMap().scan();
		});
	}
}
