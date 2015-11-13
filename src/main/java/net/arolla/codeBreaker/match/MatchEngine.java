package net.arolla.codeBreaker.match;

import net.arolla.codeBreaker.response.ResponseFormatter;

/**
 * @author Emmanuel
 *
 */
public class MatchEngine {

	private final MatchResponse response;

	public MatchEngine(String secreteCode) {
		this.response = new MatchResponse(secreteCode);
	}

	public ResponseFormatter getResponseFormatter(String guess) {
		response.build(guess);
		return new ResponseFormatter(response.getResults(), response.getExpectedMatchSize());
	}

}
