package net.arolla.codeBreaker.match;

import net.arolla.codeBreaker.response.ResponseFormatter;

/**
 * @author Emmanuel
 *
 */
public class MatchEngine {

	private final MatchProcessor processor;

	public MatchEngine(String secreteCode) {
		this.processor = new MatchProcessor(secreteCode);
	}

	public ResponseFormatter getResponseFormatter(String guess) {
		processor.init()
		       .buildUserMatch(guess)
		       .buildExactMatch();
		return new ResponseFormatter(processor.getResultMatch(), processor.getExpectedMatchSize());
	}
}
