package net.arolla.codeBreaker.match;

import java.util.Objects;

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

		public boolean isExact() {
			return EXACT.equals(this);
		}
	}
	
	public int getPosition() {
		return this.position;
	}

	public boolean equalsInValueOnly(Match obj) {
		return !Objects.equals(position, obj.position) && Objects.equals(value, obj.value);
	}

	public boolean equalsInValueAndPosition(Match obj) {
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
