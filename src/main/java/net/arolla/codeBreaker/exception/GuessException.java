package net.arolla.codeBreaker.exception;

/**
 * @author Emmanuel
 *
 */
public class GuessException extends Exception {

	private static final long serialVersionUID = 1967406633749743214L;

	public GuessException(String message) {
		super(message);
	}

	public GuessException(Throwable cause) {
		super(cause);
	}

}
