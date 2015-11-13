package net.arolla.codeBreaker.match;

import java.util.ArrayList;
import java.util.List;

public class MatchBuilder {

	private final List<Match> list;

	public MatchBuilder(String word) {
		this.list = getMatch(word);
	}

	public List<Match> get() {
		return this.list;
	}
	
	public List<Match> getMatch(String word) {
		char[] array = word.toCharArray();
		List<Match> list = new ArrayList<Match>();
		for (int i = 0; i < array.length; i++)
			list.add(new Match(i, Character.getNumericValue(array[i])));
		return list;
	}

	public static boolean isValid(String word) {
		boolean b = false;
		try {
			int num = Integer.parseInt(word);
			b = (num >= 1000 && num <= 9999);
		} catch (NumberFormatException e) {
			
		}
		return b;
	}
	
}
