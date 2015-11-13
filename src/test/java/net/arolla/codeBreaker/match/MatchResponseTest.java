package net.arolla.codeBreaker.match;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import net.arolla.codeBreaker.match.Match.MatchType;

public class MatchResponseTest {

	private MatchResponse response;

	@Before
	public void init() {
		this.response = new MatchResponse("1234");
	}
	
	@Test(expected = IllegalStateException.class)
	public void should_raise_an_illegal_state_exception_when_not_fully_built() {
		response.getResults();
	}
		
	@Test
	public void should_build_result_match_accordingly() {
		Map<Integer, MatchType> map = new HashMap<Integer, MatchType>();
		map.put(3, MatchType.EXACT);
		map.put(1, MatchType.DIGIT);
		map.put(2, MatchType.DIGIT);
		map.put(0, MatchType.EXACT);
		assertEquals(map, response.build("1324").getResults());
	}
}
