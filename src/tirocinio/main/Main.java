package tirocinio.main;

import tirocinio._di.ModuleInjector;
import tirocinio.token.reader.TokenReader;
import tirocinio.token.reader.TokenReaderFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new ModuleInjector());
		
		String csvFileName = "data/filter_eventlog.csv";
		
		TokenReaderFactory tokenReaderFactory = injector.getInstance(TokenReaderFactory.class);
		TokenReader tokenReader = tokenReaderFactory.create();
		
		System.out.print("Reading " + System.getProperty("user.dir") + csvFileName + " ");
		tokenReader.read(csvFileName);
		System.out.println("done.");
		
	}
}
