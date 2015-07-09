package logAnalyzer.dtw.options;

public class FileInException extends Exception {

	private static final long serialVersionUID = 7686181297523294330L;

	public FileInException() {
	}

	public FileInException(String message) {
		super(message);
	}

	public FileInException(Throwable cause) {
		super(cause);
	}

	public FileInException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileInException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
