package tirocinio.token.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import tirocinio.token.Tokens;

import com.google.inject.assistedinject.AssistedInject;

public class TokenReader {
	
	private final Tokens tokens;

	@AssistedInject
	public TokenReader(Tokens tokens) {
		this.tokens = tokens;
	}

	public Tokens read(String path) {
		BufferedReader bufferedReader;
		String line = "";
		String cvsSplitBy = ",";

		try {
			bufferedReader = new BufferedReader(new FileReader(path));

			while ((line = bufferedReader.readLine()) != null) {
				tokens.add(line.split(cvsSplitBy));
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
