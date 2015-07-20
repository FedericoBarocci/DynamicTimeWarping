package logAnalyzer.dtw.clioptions.exceptions;

public class InputFileException extends Exception {

	private static final long serialVersionUID = 7686181297523294330L;

	public InputFileException() {
	}

	public InputFileException(String message) {
		super(message);
	}

	public InputFileException(Throwable cause) {
		super(cause);
	}

	public InputFileException(String message, Throwable cause) {
		super(message, cause);
	}

	public InputFileException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
