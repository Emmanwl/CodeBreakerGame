package net.arolla.codeBreaker.match;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

public class MatchListBuilderTest {

	private MatchListBuilder builder;

	@Before
	public void init() {
		this.builder = new MatchListBuilder("1234");
	}
	
	@Test(expected = IllegalStateException.class)
	public void should_raise_an_illegal_state_exception_when_not_fully_built() {
		builder.getResultMatch();
	}
	
	@Test
	public void should_build_secrete_match_accordingly() {
		List<Match> match = Arrays.asList(new Match(0, 1), new Match(1, 2), new Match(2, 3), new Match(3, 4));
		assertEquals(match, builder.init().getList());
	}
	
	@Test
	public void should_build_user_match_accordingly() {
		List<Match> match = Arrays.asList(new Match(0, 1), new Match(1, 2), new Match(2, 3), new Match(3, 5));
		assertEquals(match, builder.init().buildUserMatch("1235").getList());
	}

	@Test
	public void should_build_exact_match_accordingly() {
		List<Match> match = Arrays.asList(new Match(0, 1), new Match(1, 2), new Match(2, 3));
		assertEquals(match, builder.init().buildUserMatch("1235").buildExactMatch().getList());
	}
	
	@Test
	public void should_build_secrete_minus_exact_accordingly() {
		List<Match> match = Arrays.asList(new Match(3, 4));
		assertEquals(match, builder.init().buildUserMatch("1235").buildExactMatch().buildSecreteMinusExact().getList());
	}

	@Test
	public void should_build_user_minus_exact_accordingly() {
		List<Match> match = Arrays.asList(new Match(3, 5));
		assertEquals(match, builder.init().buildUserMatch("1235").buildExactMatch().buildSecreteMinusExact().buildUserMinusExact().getList());
	}
	
	@Test
	public void should_build_result_match_accordingly() {
		TreeMap<Integer, MatchType> map = new TreeMap<Integer, MatchType>(MatchListBuilder.sortByNaturalOrdering);
		map.put(3, MatchType.EXACT);
		map.put(1, MatchType.DIGIT);
		map.put(2, MatchType.DIGIT);
		map.put(0, MatchType.EXACT);
		assertEquals(map, builder.init().buildUserMatch("1324").buildExactMatch().buildSecreteMinusExact().buildUserMinusExact().getResultMatch());
	}
}
