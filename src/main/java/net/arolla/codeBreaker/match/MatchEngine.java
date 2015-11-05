package net.arolla.codeBreaker.match;

/**
 * @author Emmanuel
 *
 */
public class MatchEngine {

	private final MatchListBuilder builder;

	public MatchEngine(String secreteCode) {
		this.builder = new MatchListBuilder(secreteCode);
	}

	public ResponseFormatter getResponseFormatter(String guess) {
		builder.buildAll(guess);
		return new ResponseFormatter(builder.getResultMatch(), builder.getExpectedMatchSize());
	}
}
