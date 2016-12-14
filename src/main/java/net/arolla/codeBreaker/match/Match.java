package net.arolla.codeBreaker.match;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public final class Match {

	private final int position;

	private final int value;

	public Match(int position, int value) {
		this.position = position;
		this.value = value;
	}

	public enum MatchType {

		EXACT("+"), DIGIT("-");

		private final String symbol;

		MatchType(String symbol) {
			this.symbol = symbol;
		}

		public String getSymbol() {
			return this.symbol;
		}

		Map<Match, MatchType> filterAccordingly(List<Match> word, List<Match> secrete) {
			Map<Match, MatchType> results = new HashMap<>();
			word.removeIf(w -> {

				boolean b = secrete.removeIf(s -> w.equalsByType(MatchType.this, s, word));
				if (b) {
					results.put(w, MatchType.this);
				}
				return b;
			});
			return results;
		}
	}

	public int getPosition() {
		return this.position;
	}

	private boolean equalsByType(MatchType type, Match match, List<Match> word) {
		if (MatchType.DIGIT.equals(type)) {
			return equalsInValueOnly(match) && !word.contains(match);
		}
		else {
			return equalsInValueAndPosition(match);
		}
	}

	private boolean equalsInValueOnly(Match obj) {
		return !Objects.equals(position, obj.position) && Objects.equals(value, obj.value);
	}

	private boolean equalsInValueAndPosition(Match obj) {
		return Objects.equals(position, obj.position) && Objects.equals(value, obj.value);
	}

	@Override public int hashCode() {
		return Objects.hash(position, value);
	}

	@Override public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		return equalsInValueAndPosition((Match) obj);
	}

	@Override public String toString() {
		return position + " # " + value;
	}
}
