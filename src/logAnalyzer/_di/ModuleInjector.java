package logAnalyzer._di;

import logAnalyzer.main.IMainExecutor;
import logAnalyzer.main.MainExecutor;

import com.google.inject.AbstractModule;

public class ModuleInjector extends AbstractModule {

	@Override
	protected void configure() {
		bind(IMainExecutor.class).to(MainExecutor.class);
	}
}
