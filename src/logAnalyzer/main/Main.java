package logAnalyzer.main;

import logAnalyzer._di.ModuleInjector;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new ModuleInjector());
		MainExecutor mainExecutor = injector.getInstance(MainExecutor.class);
		mainExecutor.start(args);
	}
}
