package net.arolla.codeBreaker.match;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.arolla.codeBreaker.match.MatchStep.Type;

/**
 * @author Emmanuel
 *
 */
public class MatchProcessor {

	private final MatchStep secrete;
	private final MatchStep user;
	private final MatchStep exact;
	private final MatchStep result;
	private final MatchStore store;

	public MatchProcessor(String secreteCode) {
		this.store = new MatchStore(buildMatchList(secreteCode));
		this.secrete = new MatchStep(Type.SECRETE, null);
		this.user = new MatchStep(Type.USER, secrete);
		this.exact = new MatchStep(Type.EXACT, user);
		this.result = new MatchStep(Type.RESULT, exact);
	}

	public MatchProcessor init() {
		store.init();
		return this;
	}

	public MatchProcessor buildUserMatch(String guess) {
		store.checkStepRequirements(user);
		store.put(Type.USER, buildMatchList(guess));
		return this;
	}

	public MatchProcessor buildExactMatch() {
		store.checkStepRequirements(exact);	
		store.putIntersect(Type.EXACT, Type.USER, Type.SECRETE);
		return this;
	}

	public List<Match> getLast() {
		return store.getLast();
	}

	public Map<Integer, MatchType> getResultMatch() {
		store.checkStepRequirements(result);

		Map<Integer, MatchType> result = new HashMap<Integer, MatchType>();
		List<Match> secreteMinusExact = store.minus(Type.SECRETE, Type.EXACT);
		for (Match m : store.get(Type.EXACT))
			result.put(m.getPosition(), MatchType.EXACT);
		for (Match m : store.minus(Type.USER, Type.EXACT)) {
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
		return store.size();
	}

	private List<Match> buildMatchList(String guess) {
		char[] array = guess.toCharArray();
		List<Match> list = new ArrayList<Match>();
		for (int i = 0; i < array.length; i++)
			list.add(new Match(i, Character.getNumericValue(array[i])));
		return list;
	}

}
