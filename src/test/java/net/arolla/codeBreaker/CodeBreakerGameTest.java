package net.arolla.codeBreaker;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Emmanuel
 *
 */
public class CodeBreakerGameTest {

	private CodeBreakerGame game;

	@Before
	public void init() {
		this.game = new CodeBreakerGame("1234");
	}

	@Test
	public void should_generate_two_exact_match_and_two_digit_match() {
		assertEquals("++--", game.playWith("1243").getMessage());
	}

	@Test
	public void should_generate_two_exact_match_and_one_digit_match() {
		assertEquals("++-", game.playWith("1245").getMessage());
	}

	@Test
	public void should_generate_a_digit_match() {
		assertEquals("-", game.playWith("2002").getMessage());
	}

	@Test
	public void should_generate_an_exact_match() {
		assertEquals("+", game.playWith("2200").getMessage());
	}

	@Test
	public void should_not_generate_any_match() {
		assertEquals("", game.playWith("5678").getMessage());
	}

	@Test
	public void should_generate_four_exact_match() {
		assertEquals("++++", game.playWith("1234").getMessage());
	}

	@Test(expected = NumberFormatException.class)
	public void should_raise_a_number_format_exception_at_game_initialization_time() {
		new CodeBreakerGame("aaa");
	}

	@Test(expected = NumberFormatException.class)
	public void should_raise_a_number_format_exception_when_playing_with_invalid_input() {
		game.playWith("aaa").getMessage();
	}

}
