package net.arolla.codeBreaker.match;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MatchBuilderTest {

	private MatchBuilder builder;

	@Before
	public void init() {
		this.builder = new MatchBuilder();
	}

	@Test
	public void should_not_validate_non_number_string() {
		assertFalse(MatchBuilder.isValid("0000"));
	}
	
	@Test
	public void should_validate_number_string() {
		assertTrue(MatchBuilder.isValid("1000"));
	}
	
	@Test
	public void should_build_word_match_accordingly() {
		List<Match> word = Arrays.asList(new Match(0, 1), new Match(1, 2), new Match(2, 3), new Match(3, 5));
		assertEquals(word, builder.getWordMatch("1235"));
	}

}
