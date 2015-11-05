package net.arolla.codeBreaker.match;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MatchListBuilderTest {

	private MatchListBuilder builder;
	private List<Match> match;

	@Before
	public void init() {
		this.builder = new MatchListBuilder("1234");
		this.match = Arrays.asList(new Match(0, 1), new Match(1, 2), new Match(2, 3), new Match(3, 5));
	}

	@Test
	public void should_build_user_match_nicely() {
		assertEquals(match, builder.getUserMatch("1235"));
	}

	@Test
	public void should_reorder_list_according_position() {
		List<Integer> ordered = Arrays.asList(0, 1, 2, 3);
		List<Integer> reversed = Arrays.asList(3, 2, 1, 0);
		Collections.sort(reversed, MatchListBuilder.sortByNaturalOrdering);
		assertEquals(ordered, reversed);
	}

	@Test(expected = IllegalStateException.class)
	public void should_raise_an_illegal_state_exception_when_not_invoked_properly() {
		builder.getResultMatch();
	}

}
