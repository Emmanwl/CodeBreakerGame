package net.arolla.codeBreaker.match;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import net.arolla.codeBreaker.match.Match.MatchType;

public class MatchFilter {

	private Map<Match, MatchType> map;
	private boolean initialized;
	private List<Match> secrete;
	private List<Match> match;
	private List<Match> word;
	
	public MatchFilter(List<Match> match) {
		this.match = match;
	}

	public MatchFilter set(List<Match> word) {
		this.secrete = new ArrayList<>(match);
		this.word = new ArrayList<>(word);
		this.map = new HashMap<>();
		this.initialized = true;
		return this;
	}

	public MatchFilter filterAccordingType(MatchType matchType) {
		if (!initialized)
			throw new IllegalStateException();
		this.map.putAll(filterAccordingType(secrete, word, matchType));
		return this;
	}

	public Map<Match, MatchType> getResults() {
		if (!initialized)
			throw new IllegalStateException();
		return this.map;
	}

	private Map<Match, MatchType> filterAccordingType(final List<Match> secrete, final List<Match> word,
			final MatchType matchType) {
		final Map<Match, MatchType> results = new HashMap<>();

		word.removeIf(new Predicate<Match>() {

			@Override
			public boolean test(final Match u) {

				boolean b = secrete.removeIf(new Predicate<Match>() {

					@Override
					public boolean test(final Match s) {
						return assertEqualsAccordingMatchType(u, s, matchType);
					}
				});

				if (b)
					results.put(u, matchType);

				return b;
			}
		});
		return results;
	}

	private boolean assertEqualsAccordingMatchType(Match u, Match s, MatchType matchType) {
		return (MatchType.EXACT.equals(matchType) ? u.equalsInValueAndPosition(s) : u.equalsInValueOnly(s));
	}

}
