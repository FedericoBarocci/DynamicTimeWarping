package logAnalizer.token.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import logAnalizer.token.Tokens;

import com.google.inject.assistedinject.AssistedInject;

public class TokenReader {
	
	private final Tokens tokens;

	@AssistedInject
	public TokenReader(Tokens tokens) {
		this.tokens = tokens;
	}

	public Tokens read(String path, int key) {
		BufferedReader bufferedReader;
		String line = "";
		String cvsSplitBy = ";";

		try {
			bufferedReader = new BufferedReader(new FileReader(path));

			while ((line = bufferedReader.readLine()) != null) {
				tokens.add(Arrays.asList(line.split(cvsSplitBy)), key);
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
