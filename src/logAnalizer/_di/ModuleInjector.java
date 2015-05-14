package logAnalizer._di;

import logAnalizer.token.reader.TokenReaderFactory;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class ModuleInjector extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().build(TokenReaderFactory.class));		
	}
}
