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

	public boolean isExact() {
		return EXACT.equals(this);
	}
	
	public static String getSymbolSuite(MatchType... matchTypes) {
		StringBuilder sb = new StringBuilder();
		for (MatchType matchType : matchTypes)
			sb.append(matchType.getSymbol());
		return sb.toString();
	}
}
