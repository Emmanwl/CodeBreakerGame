package net.arolla.codeBreaker.match; 

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MatchBuilderTest {

	private MatchBuilder builder;
	private List<Match> match;
	
	@Before
	public void init() {
		this.builder = new MatchBuilder("1234");
		this.match = Arrays.asList(new Match(0, 1), new Match(1, 2), new Match(2, 3), new Match(3, 5));
	}
	
	@Test
	public void should_build_user_match_nicely() {
		assertEquals(match, builder.buildUserMatch("1235").getUserMatch());
	}
	
	@Test(expected=IllegalStateException.class)
	public void should_raise_an_illegal_state_exception_when_builder_is_not_properly_invoked() {
		assertEquals(match, builder.getUserMatch());
	}
	
}
