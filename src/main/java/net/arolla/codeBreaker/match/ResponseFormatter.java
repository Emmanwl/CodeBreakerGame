package net.arolla.codeBreaker.match;

import java.util.Map;

/**
 * @author Emmanuel
 *
 */
public class ResponseFormatter {

	private final Map<Integer, MatchType> results;
	private final int expectedMatchSize;

	public ResponseFormatter(Map<Integer, MatchType> results, int expectedMatchSize) {
		this.results = results;
		this.expectedMatchSize = expectedMatchSize;
	}

	public String getMessage() {
		StringBuilder sb = new StringBuilder();
		for (MatchType matchType : results.values())
			sb.append(matchType.getSymbol());
		return sb.toString();
	}

	public boolean matches() {
		if (results.values().size() != expectedMatchSize)
			return false;
		for (MatchType matchType : results.values()) {
			if (!matchType.isExact())
				return false;
		}
		return true;
	}
}
