package net.arolla.codeBreaker.match;

/**
 * @author Emmanuel
 *
 */
public class MatchEngine {

	private final MatchBuilder builder;

	public MatchEngine(String secreteCode) {
		this.builder = new MatchBuilder(secreteCode);
	}

	public ResponseFormatter getResponseFormatter(String guess) {
		builder.buildAll(guess);
		return new ResponseFormatter(builder.getMatchResults(), builder.getMatchSize());
	}
}
