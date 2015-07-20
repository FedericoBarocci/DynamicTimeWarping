package logAnalyzer.dtw.clioptions.exceptions;

public class LengthInException extends Exception {

	private static final long serialVersionUID = 3499046206714928960L;

	public LengthInException() {
	}

	public LengthInException(String message) {
		super(message);
	}

	public LengthInException(Throwable cause) {
		super(cause);
	}

	public LengthInException(String message, Throwable cause) {
		super(message, cause);
	}

	public LengthInException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
