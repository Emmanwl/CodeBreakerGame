package net.arolla.codeBreaker.exception;

/**
 * @author Emmanuel
 *
 */
public class GameException extends Exception {

	private static final long serialVersionUID = 5055220797002163128L;

	public GameException(String message) {
		super(message);
	}

	public GameException(Throwable cause) {
		super(cause);
	}

}
