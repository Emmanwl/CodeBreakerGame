package net.arolla.codeBreaker.match;

public class MatchStep {

	private final MatchStep step;
	private final Type type;

	public MatchStep(Type type, MatchStep step) {
		this.type = type;
		this.step = step;
	}

	public MatchStep getNext() {
		return this.step;
	}

	public Type getType() {
		return this.type;
	}

	public enum Type {

		SECRETE, USER, EXACT, RESULT;

	}
}
