package net.arolla.codeBreaker.match;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

public class ResponseFormatterTest {

	@Test
	public void should_generate_a_valid_response() {
		TreeMap<Integer, MatchType> results = getResultsMatchingWith(MatchType.EXACT);
		ResponseFormatter response = new ResponseFormatter(results, results.size());
		assertTrue(response.matches());
	}
	
	@Test
	public void should_generate_an_invalid_response_since_there_is_at_least_one_digit_match() {
		TreeMap<Integer, MatchType> results = getResultsMatchingWith(MatchType.EXACT);
		Iterator<Map.Entry<Integer, MatchType>> iterator = results.entrySet().iterator();
		if (iterator.hasNext()) {
			iterator.next().setValue(MatchType.DIGIT);
		}
		ResponseFormatter response = new ResponseFormatter(results, results.size());
		assertFalse(response.matches());
	}	
	
	@Test
	public void should_generate_an_invalid_response_since_a_digit_has_not_been_matched() {
		TreeMap<Integer, MatchType> results = getResultsMatchingWith(MatchType.EXACT);
		ResponseFormatter response = new ResponseFormatter(results, results.size() - 1);
		assertFalse(response.matches());
	}

	private TreeMap<Integer, MatchType> getResultsMatchingWith(MatchType matchType) {
		List<Match> match = Arrays.asList(new Match(0, 1), new Match(1, 2), new Match(2, 3), new Match(3, 5));
		TreeMap<Integer, MatchType> results = new TreeMap<Integer, MatchType>();
		for (Match m : match) {
			results.put(m.getPosition(), matchType);
		}
		return results;
	}

}
