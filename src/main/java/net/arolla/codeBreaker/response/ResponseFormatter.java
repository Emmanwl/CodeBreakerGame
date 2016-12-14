package net.arolla.codeBreaker.response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import net.arolla.codeBreaker.match.Match;
import net.arolla.codeBreaker.match.Match.MatchType;

public class ResponseFormatter {

   private final static Comparator<Match> SORT_BY_NATURAL_ORDERING = (m1, m2) -> Integer.compare(m1.getPosition(), m2.getPosition());

   private final Map<Match, MatchType> results;
   private final int expectedMatchSize;

   public ResponseFormatter(Map<Match, MatchType> results, int expectedMatchSize) {
      this.results = results;
      this.expectedMatchSize = expectedMatchSize;
   }

   public String getMessage() {
      StringBuilder sb = new StringBuilder();
      List<Match> list = new ArrayList<>(results.keySet());
      Collections.sort(list, SORT_BY_NATURAL_ORDERING);
      for (Match m : list) {
         sb.append(results.get(m).getSymbol());
      }
      return sb.toString();
   }

   public boolean matches() {
      if (results.values().size() != expectedMatchSize) {
         return false;
      }
      for (MatchType matchType : results.values()) {
         if (!MatchType.EXACT.equals(matchType)) {
            return false;
         }
      }
      return true;
   }
}
