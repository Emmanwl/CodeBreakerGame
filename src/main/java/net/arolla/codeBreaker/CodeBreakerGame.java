package net.arolla.codeBreaker;

import net.arolla.codeBreaker.match.MatchEngine;
import net.arolla.codeBreaker.match.ResponseFormatter;

/**
 * @author Emmanuel
 * 
 */
public class CodeBreakerGame {

	private final MatchEngine engine;
	
	public CodeBreakerGame(String secreteCode) {
		if (!isAValidFourDigitNumber(secreteCode))
			throw new NumberFormatException(Messages.SECRETE_CODE_IS_NOT_A_VALID_FOUR_DIGIT_NUMBER);
		this.engine = new MatchEngine(secreteCode);
	}

	public ResponseFormatter playWith(String guess) {
		if (!isAValidFourDigitNumber(guess))
			throw new NumberFormatException(Messages.INPUT_IS_NOT_A_VALID_FOUR_DIGIT_NUMBER);
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
