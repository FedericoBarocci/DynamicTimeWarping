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
			return "File name of CSV database";
		}

		@Override
		public Option getOption() {
			return Option.builder(getName()).required().hasArg()
					.argName("path").desc(getDescription()).build();
		}

		@Override
		public void configure(Configuration configuration, String[] optionValue) {
			configuration.setFileNameIn(optionValue[0]);
		}
	},
	FOUT {
		@Override
		public String getName() {
			return "fout";
		}

		@Override
		protected String getDescription() {
			return "File name for output results";
		}

		@Override
		public Option getOption() {
			return Option.builder(getName()).hasArg().argName("path")
					.desc(getDescription()).build();
		}

		@Override
		public void configure(Configuration configuration, String[] optionValue) {
			configuration.setFileNameOut(optionValue[0]);
		}
	},
	FQUERY {
		@Override
		public String getName() {
			return "fquery";
		}

		@Override
		protected String getDescription() {
			return "File name of user CSV query";
		}

		@Override
		public Option getOption() {
			return Option.builder(getName()).hasArg().argName("path")
					.desc(getDescription()).build();
		}

		@Override
		public void configure(Configuration configuration, String[] optionValue) {
			configuration.setFileNameQuery(optionValue[0]);
		}
	},
	LENQUERY {
		@Override
		public String getName() {
			return "lenquery";
		}

		@Override
		protected String getDescription() {
			return "Length of query sequence";
		}

		@Override
		public Option getOption() {
			return Option.builder(getName()).hasArg().argName("int")
					.desc(getDescription()).build();
		}

		@Override
		public void configure(Configuration configuration, String[] optionValue) {
			configuration.setLenQuery(optionValue[0]);
		}
	},
	LENMATCH {
		@Override
		public String getName() {
			return "lenmatch";
		}

		@Override
		protected String getDescription() {
			return "Length of matching sequence in database";
		}

		@Override
		public Option getOption() {
			return Option.builder(getName()).hasArg().argName("int")
					.desc(getDescription()).build();
		}

		@Override
		public void configure(Configuration configuration, String[] optionValue) {
			configuration.setLenMatch(optionValue[0]);
		}
	},
	TOKENSQUERY {
		@Override
		public String getName() {
			return "tokensquery";
		}

		@Override
		protected String getDescription() {
			return "Tokens to match in query";
		}

		@Override
		public Option getOption() {
			return Option.builder(getName()).hasArgs().optionalArg(true)
					.argName("tokens> <...").numberOfArgs(Option.UNLIMITED_VALUES)
					.desc(getDescription()).build();
		}

		@Override
		public void configure(Configuration configuration, String[] optionValue) {
			configuration.setTokensQuery(optionValue);
		}
	},
	TOKENSMATCH {
		@Override
		public String getName() {
			return "tokensmatch";
		}

		@Override
		protected String getDescription() {
			return "Tokens to match in database";
		}

		@Override
		public Option getOption() {
			return Option.builder(getName()).hasArgs().optionalArg(true)
					.argName("tokens> <...").numberOfArgs(Option.UNLIMITED_VALUES)
					.desc(getDescription()).build();
		}

		@Override
		public void configure(Configuration configuration, String[] optionValue) {
			configuration.setTokensMatch(optionValue);
		}
	},
	INDEXIN {
		@Override
		public String getName() {
			return "indexin";
		}

		@Override
		protected String getDescription() {
			return "Index of date column in db file";
		}

		@Override
		public Option getOption() {
			return Option.builder(getName()).hasArg().argName("int")
					.desc(getDescription()).build();
		}

		@Override
		public void configure(Configuration configuration, String[] optionValue) {
			configuration.setIndexKeyIn(optionValue[0]);
		}
	},
	INDEXQUERY {
		@Override
		public String getName() {
			return "indexq";
		}

		@Override
		protected String getDescription() {
			return "Index of date column in query file";
		}

		@Override
		public Option getOption() {
			return Option.builder(getName()).hasArg().argName("int")
					.desc(getDescription()).build();
		}

		@Override
		public void configure(Configuration configuration, String[] optionValue) {
			configuration.setIndexKeyQuery(optionValue[0]);
		}
	},
	PRINTMATRIX {
		@Override
		public String getName() {
			return "matrix";
		}

		@Override
		protected String getDescription() {
			return "Print solution matrix of DTW algorithm";
		}

		@Override
		public Option getOption() {
			return Option.builder(getName()).hasArg(false)
					.desc(getDescription()).build();
		}

		@Override
		public void configure(Configuration configuration, String[] optionValue) {
			configuration.setPrintMatrix();
		}
	},
	PRINTDB {
		@Override
		public String getName() {
			return "printdb";
		}

		@Override
		protected String getDescription() {
			return "Print parsed input database";
		}

		@Override
		public Option getOption() {
			return Option.builder(getName()).hasArg(false)
					.desc(getDescription()).build();
		}

		@Override
		public void configure(Configuration configuration, String[] optionValue) {
			configuration.setPrintDB();
		}
	},
	HELP {
		@Override
		public String getName() {
			return "help";
		}

		@Override
		protected String getDescription() {
			return "Show this";
		}

		@Override
		public Option getOption() {
			return Option.builder(getName()).hasArg(false)
					.desc(getDescription()).build();
		}

		@Override
		public void configure(Configuration configuration, String[] optionValue) {
			configuration.setHelp();
		}
	};

	abstract public String getName();
	abstract protected String getDescription();
	abstract public Option getOption();
	abstract public void configure(Configuration configuration, String[] optionValue);
}
