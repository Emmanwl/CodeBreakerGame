package net.arolla.codeBreaker.match;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.arolla.codeBreaker.match.Match.MatchType;
import net.arolla.codeBreaker.match.MatchStore.Key;

/**
 * @author Emmanuel
 *
 */
public class MatchResponse {

	private final MatchBuilder builder;
	private final MatchStore store;

	public MatchResponse(String secreteCode) {
		this.builder = new MatchBuilder(secreteCode);
		this.store = new MatchStore();
	}

	public MatchResponse build(String guess) {
		store.init();
		store.put(Key.SECRETE, builder.get());
		store.put(Key.USER, builder.getMatch(guess));
		store.put(Key.EXACT, intersect(Key.USER, Key.SECRETE));
		return this;
	}

	public Map<Integer, MatchType> getResults() {
		if (!store.isFullyBuilt())
			throw new IllegalStateException();

		Map<Integer, MatchType> result = new HashMap<Integer, MatchType>();
		List<Match> secreteMinusExact = minus(Key.SECRETE, Key.EXACT);
		for (Match m : store.get(Key.EXACT))
			result.put(m.getPosition(), MatchType.EXACT);
		for (Match m : minus(Key.USER, Key.EXACT)) {
			Iterator<Match> iterator = secreteMinusExact.iterator();
			while (iterator.hasNext()) {
				if (m.equalsInValue(iterator.next())) {
					result.put(m.getPosition(), MatchType.DIGIT);
					iterator.remove();
					break;
				}
			}
		}
		return result;
	}

	public int getExpectedMatchSize() {
		return builder.get().size();
	}
	
	private List<Match> intersect(Key key1, Key key2) {
		List<Match> list = new ArrayList<Match>(store.get(key1));
		list.retainAll(store.get(key2));
		return  list;
	}
	
	private List<Match> minus(Key key1, Key key2) {
		List<Match> list = new ArrayList<Match>(store.get(key1));
		list.removeAll(store.get(key2));
		return list;
	}

}
