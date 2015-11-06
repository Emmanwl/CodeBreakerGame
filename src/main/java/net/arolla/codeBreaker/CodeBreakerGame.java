package net.arolla.codeBreaker;

import net.arolla.codeBreaker.exception.GameException;
import net.arolla.codeBreaker.exception.GuessException;
import net.arolla.codeBreaker.match.MatchEngine;
import net.arolla.codeBreaker.match.ResponseFormatter;

/**
 * @author Emmanuel
 * 
 */
public class CodeBreakerGame {

	private final MatchEngine engine;
	
	public CodeBreakerGame(String secreteCode) throws GameException {
		if (!isAValidFourDigitNumber(secreteCode))
			throw new GameException(Messages.SECRETE_CODE_IS_NOT_A_VALID_FOUR_DIGIT_NUMBER);
		this.engine = new MatchEngine(secreteCode);
	}

	public ResponseFormatter playWith(String guess) throws GuessException {
		if (!isAValidFourDigitNumber(guess))
			throw new GuessException(Messages.INPUT_IS_NOT_A_VALID_FOUR_DIGIT_NUMBER);
		return engine.getResponseFormatter(guess);
	}
	
	private boolean isAValidFourDigitNumber(String number) {
		boolean b = false;
		try {
			int num = Integer.parseInt(number);
			b = (num >= 1000 && num <= 9999);
		} catch (NumberFormatException e) {
			
		}
		return b;
	}

}
