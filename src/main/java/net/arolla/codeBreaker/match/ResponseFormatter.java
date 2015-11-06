package net.arolla.codeBreaker.match;

import java.util.TreeMap;

/**
 * @author Emmanuel
 *
 */
public class ResponseFormatter {

	private final TreeMap<Integer, MatchType> results;
	private final int expectedMatchSize;

	public ResponseFormatter(TreeMap<Integer, MatchType> results, int expectedMatchSize) {
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
