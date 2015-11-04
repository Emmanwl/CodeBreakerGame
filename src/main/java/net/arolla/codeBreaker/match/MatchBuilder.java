package net.arolla.codeBreaker.match;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MatchBuilder {

	private final List<Match> secreteMatch;
	private List<Match> userMatch;
	private List<Match> exactMatch;
	private List<Match> userMatchMinusExactOnes;
	private List<Match> secreteMatchMinusExactOnes;

	public MatchBuilder(String secreteCode) {
		this.secreteMatch = buildMatch(secreteCode);
	}

	public MatchBuilder buildUserMatch(String guess) {
		this.userMatch = buildMatch(guess);
		return this;
	}

	public List<Match> getUserMatch() {
		if (userMatch == null)
			throw new IllegalStateException();
		return userMatch;
	}

	public MatchBuilder buildAll(String guess) {
		return buildUserMatch(guess).buildExactMatch().buildUserMatchMinusExactOnes().buildSecreteMatchMinusExactOnes();
	}

	public Map<Match, MatchType> getMatchResults() {
		if (userMatch == null || exactMatch == null || secreteMatchMinusExactOnes == null
				|| userMatchMinusExactOnes == null)
			throw new IllegalStateException();
		Map<Match, MatchType> results = new HashMap<Match, MatchType>();
		for (Match m : exactMatch)
			results.put(m, MatchType.EXACT);
		for (Match m : userMatchMinusExactOnes) {
			Iterator<Match> iterator = secreteMatchMinusExactOnes.iterator();
			while (iterator.hasNext()) {
				if (m.equalsInValue(iterator.next())) {
					results.put(m, MatchType.DIGIT);
					iterator.remove();
					break;
				}
			}
		}
		return results;
	}

	public int getMatchSize() {
		if (userMatch == null)
			throw new IllegalStateException();
		return userMatch.size();
	}

	private List<Match> buildMatch(String guess) {
		char[] array = guess.toCharArray();
		List<Match> list = new ArrayList<Match>();
		for (int i = 0; i < array.length; i++)
			list.add(new Match(i, Character.getNumericValue(array[i])));
		return list;
	}

	private MatchBuilder buildExactMatch() {
		if (userMatch == null)
			throw new IllegalStateException();

		this.exactMatch = new ArrayList<Match>(userMatch);
		this.exactMatch.retainAll(secreteMatch);
		return this;
	}

	private MatchBuilder buildSecreteMatchMinusExactOnes() {
		if (exactMatch == null)
			throw new IllegalStateException();

		this.secreteMatchMinusExactOnes = new ArrayList<Match>(secreteMatch);
		this.secreteMatchMinusExactOnes.removeAll(exactMatch);
		return this;
	}

	private MatchBuilder buildUserMatchMinusExactOnes() {
		if (userMatch == null || exactMatch == null)
			throw new IllegalStateException();

		this.userMatchMinusExactOnes = new ArrayList<Match>(userMatch);
		this.userMatchMinusExactOnes.removeAll(exactMatch);
		return this;
	}
}
