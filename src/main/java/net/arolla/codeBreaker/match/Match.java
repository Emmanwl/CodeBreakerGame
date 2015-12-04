package net.arolla.codeBreaker.match;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * @author Emmanuel
 * 
 */
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

		private MatchType(String symbol) {
			this.symbol = symbol;
		}

		public String getSymbol() {
			return this.symbol;
		}

		private boolean isEqualAccordingly(Match w, Match s, List<Match> word) {
			if (MatchType.DIGIT.equals(this))
				return w.equalsInValueOnly(s) && !word.contains(s);
			else
				return w.equalsInValueAndPosition(s);
		}

		Map<Match, MatchType> filterAccordingly(final List<Match> word, final List<Match> secrete) {
			final Map<Match, MatchType> results = new HashMap<>();

			word.removeIf(w -> {
					boolean b = secrete.removeIf(s -> isEqualAccordingly(w, s, word));

					if (b)
						results.put(w, MatchType.this);
					return b;
			});
			return results;
		}
	}
	
	public int getPosition() {
		return this.position;
	}

	private boolean equalsInValueOnly(Match obj) {
		return !Objects.equals(position, obj.position) && Objects.equals(value, obj.value);
	}

	private boolean equalsInValueAndPosition(Match obj) {
		return Objects.equals(position, obj.position) && Objects.equals(value, obj.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(position, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		return equalsInValueAndPosition((Match) obj);
	}
	
	@Override
	public String toString() {
		return position + " # " + value;  
	}
}
