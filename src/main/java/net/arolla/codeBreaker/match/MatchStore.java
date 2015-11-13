package net.arolla.codeBreaker.match;

import java.util.EnumMap;
import java.util.List;

public class MatchStore {

	private final EnumMap<Key, List<Match>> map;

	public MatchStore() {
		this.map = new EnumMap<Key, List<Match>>(Key.class);
	}

	public enum Key {

		SECRETE, USER, EXACT;

	}

	public void init() {
		this.map.clear();
	}

	public void put(Key type, List<Match> list) {
		this.map.put(type, list);
	}

	public List<Match> get(Key type) {
		return this.map.get(type);
	}

	public boolean isFullyBuilt() {
		for (Key key : Key.values()) {
			if (!map.containsKey(key))
				return false;
		}
		return true;
	}

}
