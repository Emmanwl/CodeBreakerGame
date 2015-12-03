package net.arolla.codeBreaker.match;

import java.util.Comparator;

/**
 * @author Emmanuel
 * 
 */
public final class Match {

	public final static Comparator<Match> sortByPosition = new Comparator<Match>() {

		@Override
		public int compare(Match m1, Match m2) {
			return Integer.compare(m1.getPosition(), m2.getPosition());
		}
	};

	private final int position;
	private final int value;

	public Match(int position, int value) {
		this.position = position;
		this.value = value;
	}

<<<<<<< HEAD
=======
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
	
>>>>>>> dd09c5f... Add lambdas
	public int getPosition() {
		return this.position;
	}

	public int getValue() {
		return this.value;
	}

	public boolean equalsInValue(Match obj) {
		return (value == obj.value);
	}

	public boolean equalsInValueAndPosition(Match obj) {
		return (position == obj.position && value == obj.value);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + position;
		result = prime * result + value;
		return result;
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
