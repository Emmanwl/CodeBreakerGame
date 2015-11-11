package net.arolla.codeBreaker.match;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class MatchProcessorTest {

	private MatchProcessor processor;

	@Before
	public void init() {
		this.processor = new MatchProcessor("1234");
	}
	
	@Test(expected = IllegalStateException.class)
	public void should_raise_an_illegal_state_exception_when_not_fully_built() {
		processor.getResultMatch();
	}
	
	@Test
	public void should_build_secrete_match_accordingly() {
		List<Match> match = Arrays.asList(new Match(0, 1), new Match(1, 2), new Match(2, 3), new Match(3, 4));
		assertEquals(match, processor.init().getLast());
	}
	
	@Test
	public void should_build_user_match_accordingly() {
		List<Match> match = Arrays.asList(new Match(0, 1), new Match(1, 2), new Match(2, 3), new Match(3, 5));
		assertEquals(match, processor.init().buildUserMatch("1235").getLast());
	}

	@Test
	public void should_build_exact_match_accordingly() {
		List<Match> match = Arrays.asList(new Match(0, 1), new Match(1, 2), new Match(2, 3));
		assertEquals(match, processor.init().buildUserMatch("1235").buildExactMatch().getLast());
	}
		
	@Test
	public void should_build_result_match_accordingly() {
		Map<Integer, MatchType> map = new HashMap<Integer, MatchType>();
		map.put(3, MatchType.EXACT);
		map.put(1, MatchType.DIGIT);
		map.put(2, MatchType.DIGIT);
		map.put(0, MatchType.EXACT);
		assertEquals(map, processor.init().buildUserMatch("1324").buildExactMatch().getResultMatch());
	}
}
