package logAnalyzer.timeSeries;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NavigableMap;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.TreeMap;

import logAnalyzer.printer.Printer;
import logAnalyzer.token.keeper.TokenKeeper;
import logAnalyzer.token.keeper.TokenKeeperFactory;
import logAnalyzer.token.map.TokenMap;

import org.apache.commons.lang3.time.DateUtils;

public class TimeSeries {

	private final DateFormat target = new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss.SSSSSS");

	private NavigableMap<Date, TokenKeeper> tokens;

	public TimeSeries() {
		this.tokens = new TreeMap<Date, TokenKeeper>();
	}
	
	public TimeSeries(NavigableMap<Date, TokenKeeper> tokens) {
		this.tokens = tokens;
	}
	
	public TokenMap unify() {
		TokenMap app = new TokenMap();
		
		tokens.values().forEach(c->{
			app.putAll(c.getTokenMap());
		});
		
		return app;
	}
	
	public void add(List<String> elements, int key) throws ParseException {
		Date dateKey;
		
		Date date = target.parse(elements.get(key));
		dateKey = DateUtils.truncate(date, Calendar.HOUR);
		
		elements.remove(key);
		
		if(tokens.containsKey(dateKey)) {
			tokens.get(dateKey).add(elements);
		}
		else {
			tokens.put(dateKey, TokenKeeperFactory.create(elements));
		}
	}

	public int size() {
		return tokens.size();
	}
	
	public TokenMap getIndex(int i, Optional<List<String>> optionalFilter) {
		TokenMap tokenMap = ((TokenKeeper) tokens.values().toArray()[i]).getTokenMap();
		
		try {
			List<String> filter = optionalFilter.get();
			TokenMap filteredMap = new TokenMap();
			
			filter.forEach(c->filteredMap.put(c, tokenMap.get(c)));
			//elements = elements.stream().filter(p -> filter.contains(p)).collect(Collectors.toList());

			return filteredMap;
		} catch (NoSuchElementException e) {
		}
		
		return tokenMap;
	}
	
	public Date getKey(int i) {
		return (Date) tokens.keySet().toArray()[i];
	}
	
	public void scan() {
		Printer.get().println(" -> Time intervals count: " + size());
		Printer.get().println(" -> Scan:");
		
		for(int i = 0; i< tokens.size(); i++) {
			Printer.get().println("" + i + ". " + tokens.keySet().toArray()[i]);
			((TokenKeeper) (tokens.values().toArray()[i])).scan();
		}
	}
	
	public TimeSeries getSegment(int start, int end) {
		Date arraydate[] = tokens.keySet().toArray(new Date[0]);
		Date d1 = arraydate[start];
		Date d2 = arraydate[end];
		
		return new TimeSeries(tokens.subMap(d1, true, d2, true));
	}
}
