package net.arolla.codeBreaker.match;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class MatchTest {

	@Test
	public void should_reorder_match_according_position() {
		List<Match> ordered = Arrays.asList(new Match(0, 1), new Match(1, 2), new Match(2, 3), new Match(3, 5));
		List<Match> reversed = Arrays.asList(new Match(3, 5), new Match(2, 3), new Match(1, 2), new Match(0, 1));
		Collections.sort(reversed, Match.sortByPosition);
		assertEquals(ordered, reversed);
	}

}
