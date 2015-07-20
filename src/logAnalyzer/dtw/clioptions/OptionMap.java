package logAnalyzer.dtw.clioptions;

import java.util.HashMap;
import java.util.Optional;

import org.apache.commons.cli.Option;

public class OptionMap extends HashMap<Option, OptionValues> {

	private static final long serialVersionUID = -1086474847053463017L;
	
	public OptionMap() {
		for(OptionValues opt : OptionValues.values()) {
			this.put(opt.getOption(), opt);
		}
	}
	
	public Optional<OptionValues> get(Option key) {
		return Optional.ofNullable(super.get(key));
	}
}
