package net.arolla.codeBreaker;

import net.arolla.codeBreaker.exception.GameException;
import net.arolla.codeBreaker.exception.GuessException;
import net.arolla.codeBreaker.match.MatchBuilder;
import net.arolla.codeBreaker.match.MatchEngine;
import net.arolla.codeBreaker.response.ResponseFormatter;

public class CodeBreaker {

   private final MatchEngine engine;

   public CodeBreaker(String secreteCode) throws GameException {
      if (!MatchBuilder.isValid(secreteCode)) {
         throw new GameException(Messages.SECRETE_CODE_IS_NOT_A_VALID_FOUR_DIGIT_NUMBER);
      }
      this.engine = new MatchEngine(secreteCode);
   }

   public ResponseFormatter playWith(String guess) throws GuessException {
      if (!MatchBuilder.isValid(guess)) {
         throw new GuessException(Messages.INPUT_IS_NOT_A_VALID_FOUR_DIGIT_NUMBER);
      }
      return engine.getResponseFormatter(guess);
   }
}
