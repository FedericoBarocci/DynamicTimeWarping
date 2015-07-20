package logAnalyzer._di;

import logAnalyzer.main.IMainExecutor;
import logAnalyzer.main.MainExecutor;

import com.google.inject.AbstractModule;

public class ModuleInjector extends AbstractModule {

	@Override
	protected void configure() {
//		install(new FactoryModuleBuilder().build(CliOptionsFactory.class));
//		install(new FactoryModuleBuilder().build(TokenReaderFactory.class));
//		install(new FactoryModuleBuilder().build(DTWFactory.class));
		bind(IMainExecutor.class).to(MainExecutor.class);
	}
}
