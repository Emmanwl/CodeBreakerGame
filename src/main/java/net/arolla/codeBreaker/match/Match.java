package net.arolla.codeBreaker.match;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * @author Emmanuel
 */
public final class Match {

   private final int position;
   private final int value;

   public Match(int position, int value) {
      this.position = position;
      this.value = value;
   }

   public enum MatchType {

      EXACT("+"), DIGIT("-");

      private final String symbol;

      MatchType(String symbol) {
         this.symbol = symbol;
      }

      public String getSymbol() {
         return this.symbol;
      }

      Map<Match, MatchType> filterAccordingly(final List<Match> word, final List<Match> secrete) {
         Map<Match, MatchType> results = new HashMap<>();

         word.removeIf(w -> {
            boolean b = secrete.removeIf(s -> w.equals(MatchType.this, s, word));

            if (b)
               results.put(w, MatchType.this);
            return b;
         });
         return results;
      }
   }

   public int getPosition() {
      return this.position;
   }

   private boolean equals(MatchType type, Match obj, List<Match> list) {
      if (MatchType.DIGIT.equals(type))
         return equalsInValueOnly(obj) && !list.contains(obj);
      else
         return equalsInValueAndPosition(obj);
   }

   private boolean equalsInValueOnly(Match obj) {
      return !Objects.equals(position, obj.position) && Objects.equals(value, obj.value);
   }

   private boolean equalsInValueAndPosition(Match obj) {
      return Objects.equals(position, obj.position) && Objects.equals(value, obj.value);
   }

   @Override
   public int hashCode() {
      return Objects.hash(position, value);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      return equalsInValueAndPosition((Match) obj);
   }

   @Override
   public String toString() {
      return position + " # " + value;
   }
}
