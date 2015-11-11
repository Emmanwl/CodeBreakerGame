package net.arolla.codeBreaker.response;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import net.arolla.codeBreaker.match.MatchType;

/**
 * @author Emmanuel
 *
 */
public class ResponseFormatter {

	private final static Comparator<Integer> sortByNaturalOrdering = new Comparator<Integer>() {

		@Override
		public int compare(Integer i1, Integer i2) {
			return Integer.compare(i1, i2);
		}
	};
	
	private final TreeMap<Integer, MatchType> results;
	private final int expectedMatchSize;

	public ResponseFormatter(Map<Integer, MatchType> results, int expectedMatchSize) {
		this.results = new TreeMap<Integer, MatchType>(sortByNaturalOrdering);
		this.results.putAll(results);
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
