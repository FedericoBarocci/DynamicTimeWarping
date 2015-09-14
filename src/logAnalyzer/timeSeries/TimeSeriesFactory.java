package logAnalyzer.timeSeries;

import java.util.Date;
import java.util.NavigableMap;

import logAnalyzer.token.keeper.TokenKeeper;

public class TimeSeriesFactory {
	
	public TimeSeries build() {
		return new TimeSeries();
	}
	
	public TimeSeries build(NavigableMap<Date, TokenKeeper> tokens) {
		return new TimeSeries(tokens);
	}
}
