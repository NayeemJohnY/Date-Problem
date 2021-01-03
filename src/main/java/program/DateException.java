package program;

public final class DateException extends IllegalArgumentException{

	private static final long serialVersionUID = 1L;

	public DateException(String message, Throwable e) {
		super(message, e);
	}

	public DateException(String message) {
		super(message);
	}
}
