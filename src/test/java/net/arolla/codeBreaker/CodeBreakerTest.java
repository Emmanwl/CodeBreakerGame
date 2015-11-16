package net.arolla.codeBreaker;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import net.arolla.codeBreaker.exception.GameException;
import net.arolla.codeBreaker.exception.GuessException;
import net.arolla.codeBreaker.match.Match.MatchType;

/**
 * 
 * @author Emmanuel
 *
 */
public class CodeBreakerTest {

	private CodeBreaker game;

	@Before
	public void init() throws Exception {
		this.game = new CodeBreaker("1234");
	}

	@Test
	public void should_generate_two_exact_match_and_two_digit_match() throws Exception {
		assertEquals(getSymbolSuite(MatchType.EXACT, MatchType.EXACT, MatchType.DIGIT, MatchType.DIGIT),
				game.playWith("1243").getMessage());
	}

	@Test
	public void should_generate_two_exact_match_and_one_digit_match() throws Exception {
		assertEquals(getSymbolSuite(MatchType.EXACT, MatchType.EXACT, MatchType.DIGIT),
				game.playWith("1245").getMessage());
	}

	@Test
	public void should_generate_a_digit_match() throws Exception {
		assertEquals(getSymbolSuite(MatchType.DIGIT), game.playWith("2002").getMessage());
	}

	@Test
	public void should_generate_an_exact_match() throws Exception {
		assertEquals(getSymbolSuite(MatchType.EXACT), game.playWith("2200").getMessage());
	}

	@Test
	public void should_not_generate_any_match() throws Exception {
		assertEquals(getSymbolSuite(), game.playWith("5678").getMessage());
	}

	@Test
	public void should_generate_four_exact_match() throws Exception {
		assertEquals(getSymbolSuite(MatchType.EXACT, MatchType.EXACT, MatchType.EXACT, MatchType.EXACT),
				game.playWith("1234").getMessage());
	}

	@Test(expected = GameException.class)
	public void should_raise_a_game_exception_at_initialization_time() throws Exception {
		new CodeBreaker("0000");
	}

	@Test(expected = GuessException.class)
	public void should_raise_a_guess_exception_when_playing_with_invalid_input() throws Exception{
		game.playWith("0000").getMessage();
	}

	private String getSymbolSuite(MatchType... matchTypes) {
		StringBuilder sb = new StringBuilder();
		for (MatchType matchType : matchTypes)
			sb.append(matchType.getSymbol());
		return sb.toString();
	}
}
