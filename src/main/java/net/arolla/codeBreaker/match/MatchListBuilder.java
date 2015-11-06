package net.arolla.codeBreaker.match;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

/**
 * @author Emmanuel
 *
 */
public class MatchListBuilder {

	public final static Comparator<Integer> sortByNaturalOrdering = new Comparator<Integer>() {

		@Override
		public int compare(Integer i1, Integer i2) {
			return Integer.compare(i1, i2);
		}
	};
	
	private enum MatchListType {

		SECRETE, USER, EXACT, USER_MINUS_EXACT, SECRETE_MINUS_EXACT;

	}

	private final EnumMap<MatchListType, List<Match>> map;
	private final List<Match> match;
	private MatchListType builtType;
	
	public MatchListBuilder(String secreteCode) {
		this.map = new EnumMap<MatchListType, List<Match>>(MatchListType.class);
		this.match = buildMatchList(secreteCode);
	}

	public MatchListBuilder init() {
		map.clear();
		map.put(builtType = MatchListType.SECRETE, match);
		return this;
	}

	public MatchListBuilder buildUserMatch(String guess) {
		requiresTypes(MatchListType.SECRETE);
		map.put(builtType = MatchListType.USER, buildMatchList(guess));
		return this;
	}

	public MatchListBuilder buildExactMatch() {
		requiresTypes(MatchListType.SECRETE, MatchListType.USER);

		List<Match> match = new ArrayList<Match>(map.get(MatchListType.USER));
		match.retainAll(map.get(MatchListType.SECRETE));
		map.put(builtType = MatchListType.EXACT, match);
		return this;
	}

	public MatchListBuilder buildSecreteMinusExact() {
		requiresTypes(MatchListType.SECRETE, MatchListType.USER, MatchListType.EXACT);

		List<Match> match = new ArrayList<Match>(map.get(MatchListType.SECRETE));
		match.removeAll(map.get(MatchListType.EXACT));
		map.put(builtType = MatchListType.SECRETE_MINUS_EXACT, match);
		return this;
	}

	public MatchListBuilder buildUserMinusExact() {
		requiresTypes(MatchListType.SECRETE, MatchListType.USER, MatchListType.EXACT);

		List<Match> match = new ArrayList<Match>(map.get(MatchListType.USER));
		match.removeAll(map.get(MatchListType.EXACT));
		map.put(builtType = MatchListType.USER_MINUS_EXACT, match);
		return this;
	}

	public List<Match> getList() {
		if (!map.containsKey(builtType))
			throw new IllegalStateException();
		return map.get(builtType);
	}
	
	public TreeMap<Integer, MatchType> getResultMatch() {
		requiresTypes(MatchListType.SECRETE, MatchListType.USER, MatchListType.EXACT, MatchListType.SECRETE_MINUS_EXACT,
				MatchListType.USER_MINUS_EXACT);

		TreeMap<Integer, MatchType> resultMatch = new TreeMap<Integer, MatchType>(sortByNaturalOrdering);
		for (Match m : map.get(MatchListType.EXACT))
			resultMatch.put(m.getPosition(), MatchType.EXACT);
		for (Match m : map.get(MatchListType.USER_MINUS_EXACT)) {
			Iterator<Match> iterator = map.get(MatchListType.SECRETE_MINUS_EXACT).iterator();
			while (iterator.hasNext()) {
				if (m.equalsInValue(iterator.next())) {
					resultMatch.put(m.getPosition(), MatchType.DIGIT);
					iterator.remove();
					break;
				}
			}
		}
		return resultMatch;
	}

	public int getExpectedMatchSize() {
		return match.size();
	}

	private List<Match> buildMatchList(String guess) {
		char[] array = guess.toCharArray();
		List<Match> list = new ArrayList<Match>();
		for (int i = 0; i < array.length; i++)
			list.add(new Match(i, Character.getNumericValue(array[i])));
		return list;
	}

	private void requiresTypes(MatchListType... types) {
		for (MatchListType type : types) {
			if (!map.containsKey(type))
				throw new IllegalStateException();
		}
	}
	
}
