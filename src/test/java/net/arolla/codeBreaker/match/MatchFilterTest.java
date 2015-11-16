package net.arolla.codeBreaker.match;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import net.arolla.codeBreaker.match.Match.MatchType;

public class MatchFilterTest {

	private MatchFilter filter;
	
	@Before
	public void init() {
		this.filter = new MatchFilter(Arrays.asList(new Match(0, 1), new Match(1, 2), new Match(2, 3), new Match(3, 5)));
	}
	
	@Test
	public void should_map_digit_match_only() {
		List<Match> word =  Arrays.asList(new Match(0, 5), new Match(1, 3), new Match(2, 2), new Match(3, 1));
		Map<Match, MatchType> map = new HashMap<>();
		map.put(word.get(0), MatchType.DIGIT);
		map.put(word.get(1), MatchType.DIGIT);
		map.put(word.get(2), MatchType.DIGIT);
		map.put(word.get(3), MatchType.DIGIT);
		assertEquals(map, filter.set(word).filterAccordingType(MatchType.DIGIT).getResults());
	}
	
	@Test
	public void should_map_exact_match_only() {
		List<Match> word =  Arrays.asList(new Match(0, 1), new Match(1, 3), new Match(2, 2), new Match(3, 5));
		Map<Match, MatchType> map = new HashMap<>();
		map.put(word.get(0), MatchType.EXACT);
		map.put(word.get(3), MatchType.EXACT);
		assertEquals(map, filter.set(word).filterAccordingType(MatchType.EXACT).getResults());
	}
	
	@Test
	public void should_map_any_match() {
		List<Match> word = Arrays.asList(new Match(0, 1), new Match(1, 3), new Match(2, 2), new Match(3, 5));
		Map<Match, MatchType> map = new HashMap<>();
		map.put(word.get(0), MatchType.EXACT);
		map.put(word.get(1), MatchType.DIGIT);
		map.put(word.get(2), MatchType.DIGIT);
		map.put(word.get(3), MatchType.EXACT);
		assertEquals(map, filter.set(word).filterAccordingType(MatchType.EXACT).filterAccordingType(MatchType.DIGIT).getResults());
	}
	
}
