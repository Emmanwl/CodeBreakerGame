package net.arolla.codeBreaker.match;

/**
 * @author Emmanuel
 *
 */
public enum MatchType {

	EXACT("+"), DIGIT("-");
	
	private final String symbol;
	private MatchType(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return this.symbol;
	}
}
