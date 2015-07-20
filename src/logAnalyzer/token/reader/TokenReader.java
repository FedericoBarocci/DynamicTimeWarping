package logAnalyzer.token.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import logAnalyzer.timeSeries.TimeSeries;

public class TokenReader {
	
	private final TimeSeries tokens;

	//@AssistedInject
	@Inject
	public TokenReader(TimeSeries tokens) {
		this.tokens = tokens;
	}

	public TimeSeries read(String path, int key, String cvsSplitBy) {
		List<String> values = new ArrayList<String>();
		BufferedReader bufferedReader;
		String line = "";

		try {
			bufferedReader = new BufferedReader(new FileReader(path));

			while ((line = bufferedReader.readLine()) != null) {
				values.addAll(Arrays.asList(line.split(cvsSplitBy)));
				tokens.add(values, key);
				values.clear();
			}
			
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return tokens;
	}
}
