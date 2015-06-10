package logAnalyzer._di;

import logAnalyzer.dtw.DTWFactory;
import logAnalyzer.token.reader.TokenReaderFactory;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class ModuleInjector extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().build(TokenReaderFactory.class));
		install(new FactoryModuleBuilder().build(DTWFactory.class));
	}
}
