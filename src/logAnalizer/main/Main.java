package logAnalizer.main;

import logAnalizer._di.ModuleInjector;
import logAnalizer.token.Tokens;
import logAnalizer.token.reader.TokenReader;
import logAnalizer.token.reader.TokenReaderFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new ModuleInjector());
		
		String csvFileName = "data/filter_eventlog.csv";
		
		TokenReaderFactory tokenReaderFactory = injector.getInstance(TokenReaderFactory.class);
		TokenReader tokenReader = tokenReaderFactory.create();
		
		System.out.print("Reading " + System.getProperty("user.dir") + csvFileName + " ");
		Tokens tokens = tokenReader.read(csvFileName, 2);
		System.out.println("done.");
		System.out.println(" -> Lines: " + tokens.getTokens().size());
	}
}
