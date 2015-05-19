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
		
		System.out.print("Reading " + System.getProperty("user.dir") + csvFileName + " ... ");
		Tokens tokens = tokenReader.read(csvFileName, 2);
		System.out.println("done.");
		
		System.out.println(" -> Time intervals count: " + tokens.size());
		
		System.out.println(" -> Scan:");
		tokens.scan();
		
		System.out.println(" -> Global tokens count:");
		tokens.unify().getTokenMap().get().forEach((key,value)->{
			System.out.println(key + " \t-> " + value);
		});
	}
}
