package net.arolla.codeBreaker.match;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Emmanuel
 *
 */
public class ResponseFormatter {

	private final Map<Match, MatchType> results;
	private final List<Match> matches;
	private final int matchSize;

	public ResponseFormatter(Map<Match, MatchType> results, int matchSize) {
		this.matches = new ArrayList<Match>(results.keySet());
		this.results = results;
		this.matchSize = matchSize;
	}

	public String getMessage() {
		Collections.sort(matches, Match.sortByPosition);
		StringBuilder sb = new StringBuilder();
		for (Match match : matches)
			sb.append(results.get(match).getSymbol());
		return sb.toString();
	}

	public boolean matches() {
		if (matches.size() != matchSize)
			return false;
		for (Match match : matches) {
			if (!MatchType.EXACT.equals(results.get(match)))
				return false;
		}
		return true;
	}
}
