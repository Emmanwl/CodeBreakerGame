package net.arolla.codeBreaker.match;

import java.util.ArrayList;
import java.util.List;

public class MatchBuilder {

   public static boolean isValid(String word) {
      boolean b = false;
      try {
         int num = Integer.parseInt(word);
         b = (num >= 1000 && num <= 9999);
      } catch (NumberFormatException e) {

      }
      return b;
   }

   public List<Match> getWordMatch(String word) {
      char[] array = word.toCharArray();
      List<Match> list = new ArrayList<>();
      for (int i = 0; i < array.length; i++)
         list.add(new Match(i, Character.getNumericValue(array[i])));
      return list;
   }
}
