package net.arolla.codeBreaker.match;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MatchListBuilder {

	public final static Comparator<Integer> sortByNaturalOrdering = new Comparator<Integer>() {

		@Override
		public int compare(Integer i1, Integer i2) {
			return Integer.compare(i1, i2);
		}
	};
	
	private final List<Match> secreteMatch;
	private List<Match> userMatch;
	private List<Match> exactMatch;
	private List<Match> userMatchMinusExactOnes;
	private List<Match> secreteMatchMinusExactOnes;
	private Map<Integer, MatchType> resultMatch;

	public MatchListBuilder(String secreteCode) {
		this.secreteMatch = buildMatch(secreteCode);
	}
	
	public MatchListBuilder buildAll(String guess) {
		return buildUserMatch(guess)
			   .buildExactMatch()
			   .buildUserMatchMinusExactOnes()
			   .buildSecreteMatchMinusExactOnes()
			   .buildResultMatch();
	}
	
	public List<Match> getUserMatch(String guess) {
		return buildMatch(guess);
	}
	
	public Map<Integer, MatchType> getResultMatch() {
		if (this.resultMatch == null)
			throw new IllegalStateException();
		return this.resultMatch;
	}

	public int getExpectedMatchSize() {
		return this.secreteMatch.size();
	}

	private List<Match> buildMatch(String guess) {
		char[] array = guess.toCharArray();
		List<Match> list = new ArrayList<Match>();
		for (int i = 0; i < array.length; i++)
			list.add(new Match(i, Character.getNumericValue(array[i])));
		return list;
	}

	private MatchListBuilder buildUserMatch(String guess) {
		this.userMatch = buildMatch(guess);
		return this;
	}
	
	private MatchListBuilder buildExactMatch() {
		if (this.userMatch == null)
			throw new IllegalStateException();

		this.exactMatch = new ArrayList<Match>(this.userMatch);
		this.exactMatch.retainAll(this.secreteMatch);
		return this;
	}

	private MatchListBuilder buildSecreteMatchMinusExactOnes() {
		if (this.exactMatch == null)
			throw new IllegalStateException();

		this.secreteMatchMinusExactOnes = new ArrayList<Match>(this.secreteMatch);
		this.secreteMatchMinusExactOnes.removeAll(this.exactMatch);
		return this;
	}

	private MatchListBuilder buildUserMatchMinusExactOnes() {
		if (this.userMatch == null || this.exactMatch == null)
			throw new IllegalStateException();

		this.userMatchMinusExactOnes = new ArrayList<Match>(this.userMatch);
		this.userMatchMinusExactOnes.removeAll(this.exactMatch);
		return this;
	}
	
	private MatchListBuilder buildResultMatch() {
		if (this.userMatch == null || this.exactMatch == null || this.secreteMatchMinusExactOnes == null
				|| this.userMatchMinusExactOnes == null)
			throw new IllegalStateException();
		this.resultMatch = new TreeMap<Integer, MatchType>(sortByNaturalOrdering);
		for (Match m : this.exactMatch)
			this.resultMatch.put(m.getPosition(), MatchType.EXACT);
		for (Match m : this.userMatchMinusExactOnes) {
			Iterator<Match> iterator = this.secreteMatchMinusExactOnes.iterator();
			while (iterator.hasNext()) {
				if (m.equalsInValue(iterator.next())) {
					this.resultMatch.put(m.getPosition(), MatchType.DIGIT);
					iterator.remove();
					break;
				}
			}
		}
		return this;
	}
}
