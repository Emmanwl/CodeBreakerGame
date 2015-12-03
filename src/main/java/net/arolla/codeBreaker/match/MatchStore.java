package net.arolla.codeBreaker.match;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import net.arolla.codeBreaker.match.MatchStep.Type;

public class MatchStore {

	private final EnumMap<Type, List<Match>> map;
	private final List<Match> list;
	private Type type;

	public MatchStore(List<Match> list) {
		this.list = list;
		this.type = Type.SECRETE;
		this.map = new EnumMap<Type, List<Match>>(Type.class);
	}

	public void init() {
		this.map.clear();
		this.map.put(Type.SECRETE, this.list);
	}

	public void put(Type type, List<Match> list) {
		this.type = type;
		this.map.put(type, list);
	}

	public List<Match> get(Type type) {
		return map.get(type);
	}

	public List<Match> getLast() {
		if (!map.containsKey(type))
			throw new IllegalStateException();
		return get(type);
	}

	public int size() {
		return list.size();
	}

	public void checkStepRequirements(MatchStep step) {
		while (step.getNext() != null) {
			step = step.getNext();
			if (!map.containsKey(step.getType()))
				throw new IllegalStateException();
		}
	}

	public List<Match> minus(Type type1, Type type2) {
		List<Match> list = new ArrayList<Match>(get(type1));
		list.removeAll(get(type2));
		return list;
	}

	public void putIntersect(Type type1, Type type2, Type type3) {
		List<Match> list = new ArrayList<Match>(get(type2));
		list.retainAll(get(type3));
		put(type1, list);
	}
}
