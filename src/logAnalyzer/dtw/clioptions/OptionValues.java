package logAnalyzer.dtw.clioptions;

import logAnalyzer.configuration.Configuration;

import org.apache.commons.cli.Option;

public enum OptionValues {
	FIN {
		@Override
		public String getName() {
			return "fin";
		}

		@Override
		protected String getDescription() {
			return "Input data file name";
		}

		@Override
		public Option getOption() {
			return Option.builder(getName()).required().hasArgs()/*.argName(getName())*/
					.desc(getDescription()).build();
		}

		@Override
		public void configure(Configuration configuration, String[] optionValue) {
			configuration.setFileNameIn(optionValue);
		}
	}, FOUT {
		@Override
		public String getName() {
			return "fout";
		}

		@Override
		protected String getDescription() {
			return "Output results file name";
		}

		@Override
		public Option getOption() {
			return Option.builder(getName()).hasArg().argName(getName())
					.desc(getDescription()).build();
		}

		@Override
		public void configure(Configuration configuration, String[] optionValue) {
			configuration.setFileNameOut(optionValue[0]);
		}
	}, FQUERY {
		@Override
		public String getName() {
			return "fquery";
		}

		@Override
		protected String getDescription() {
			return "Query file name";
		}

		@Override
		public Option getOption() {
			return Option.builder(getName()).hasArgs().argName(getName())
					.desc(getDescription()).build();
		}

		@Override
		public void configure(Configuration configuration, String[] optionValue) {
			configuration.setFileNameQuery(optionValue[0]);
		}
	}, LIN {
		@Override
		public String getName() {
			return "lin";
		}

		@Override
		protected String getDescription() {
			return "Length of input subsequence";
		}

		@Override
		public Option getOption() {
			return Option.builder(getName()).hasArg().argName(getName())
					.desc(getDescription()).build();
		}

		@Override
		public void configure(Configuration configuration, String[] optionValue) {
			configuration.setLenSeqIn(optionValue[0]);
		}
	}, LOUT {
		@Override
		public String getName() {
			return "lout";
		}

		@Override
		protected String getDescription() {
			return "Length of output subsequence";
		}

		@Override
		public Option getOption() {
			return Option.builder(getName()).hasArg().argName(getName())
					.desc(getDescription()).build();
		}

		@Override
		public void configure(Configuration configuration, String[] optionValue) {
			configuration.setLenSeqOut(optionValue[0]);
		}
	}, TOKENSIN {
		@Override
		public String getName() {
			return "tokensin";
		}

		@Override
		protected String getDescription() {
			return "Token to match in data";
		}

		@Override
		public Option getOption() {
			return Option.builder(getName()).hasArgs().argName(getName())
					.desc(getDescription()).build();
		}

		@Override
		public void configure(Configuration configuration, String[] optionValue) {
			configuration.setTokensIn(optionValue[0]);
		}
	}, TOKENSOUT {
		@Override
		public String getName() {
			return "tokensout";
		}

		@Override
		protected String getDescription() {
			return "Token to match in query";
		}

		@Override
		public Option getOption() {
			return Option.builder(getName()).hasArgs().argName(getName())
					.desc(getDescription()).build();
		}

		@Override
		public void configure(Configuration configuration, String[] optionValue) {
			configuration.setTokensOut(optionValue[0]);
		}
	}, INDEXIN {
		@Override
		public String getName() {
			return "idxin";
		}

		@Override
		protected String getDescription() {
			return "Index of date column in input file";
		}

		@Override
		public Option getOption() {
			return Option.builder(getName()).hasArg().argName(getName())
					.desc(getDescription()).build();
		}

		@Override
		public void configure(Configuration configuration, String[] optionValue) {
			configuration.setIndexKeyIn(optionValue[0]);
		}
	}, INDEXQUERY {
		@Override
		public String getName() {
			return "idxq";
		}

		@Override
		protected String getDescription() {
			return "Index of date column in query file";
		}

		@Override
		public Option getOption() {
			return Option.builder(getName()).hasArg().argName(getName())
					.desc(getDescription()).build();
		}

		@Override
		public void configure(Configuration configuration, String[] optionValue) {
			configuration.setIndexKeyQuery(optionValue[0]);
		}
	}, PRINTMATRIX {
		@Override
		public String getName() {
			return "matrix";
		}

		@Override
		protected String getDescription() {
			return "Print matrix of results";
		}

		@Override
		public Option getOption() {
			return Option.builder(getName()).hasArg(false).argName(getName())
					.desc(getDescription()).build();
		}

		@Override
		public void configure(Configuration configuration, String[] optionValue) {
			configuration.setPrintMatrix();
		}
	}, PRINTDB {
		@Override
		public String getName() {
			return "printdb";
		}

		@Override
		protected String getDescription() {
			return "Print input db";
		}

		@Override
		public Option getOption() {
			return Option.builder(getName()).hasArg(false).argName(getName())
					.desc(getDescription()).build();
		}

		@Override
		public void configure(Configuration configuration, String[] optionValue) {
			configuration.setPrintDB();
		}
	};
	
	abstract public String getName();
	abstract protected String getDescription();
	abstract public Option getOption();
	abstract public void configure(Configuration configuration, String[] optionValue);
	
	//private static Configuration configuration = ConfigurationProvider.get();
}
