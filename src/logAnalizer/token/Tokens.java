package logAnalizer.token;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

public class Tokens {

	private final DateFormat target = new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss.SSSSSS");

	//private List<String[]> tokens = new ArrayList<>();
	private NavigableMap<Date, String> tokens = new TreeMap<Date, String>();

	public Collection<String> getTokens() {
		return tokens.values();
	}
	
	public void add(List<String> elements, int key) {
		List<String> filtered = new ArrayList<String>();
		
		for(int i=0; i<elements.size(); i++) {
			if(i != key) {
				filtered.add(elements.get(i));
			}
		}
		
		String values = String.join(",", filtered);
		/*Timestamp.valueOf(elements.get(key))*/
		
		try {
			tokens.put(target.parse(elements.get(key)), values);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
