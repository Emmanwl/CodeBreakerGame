package net.arolla.codeBreaker.match;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.arolla.codeBreaker.match.Match.MatchType;

public class MatchFilter {

   private Map<Match, MatchType> map;
   private List<Match> secrete;
   private List<Match> match;
   private List<Match> word;

   public MatchFilter(List<Match> match) {
      this.match = match;
   }

   public MatchFilter from(List<Match> word) {
      this.secrete = new ArrayList<>(match);
      this.word = new ArrayList<>(word);
      this.map = new HashMap<>();
      return this;
   }

   public MatchFilter filterAccordingly(MatchType matchType) {
      if (map == null)
         throw new IllegalStateException();
      this.map.putAll(matchType.filterAccordingly(word, secrete));
      return this;
   }

   public Map<Match, MatchType> getResults() {
      if (map == null)
         throw new IllegalStateException();
      return this.map;
   }
}
