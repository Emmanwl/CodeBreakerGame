package net.arolla.codeBreaker;

import java.util.Random;
import java.util.Scanner;

import net.arolla.codeBreaker.exception.GameException;
import net.arolla.codeBreaker.exception.GuessException;
import net.arolla.codeBreaker.response.ResponseFormatter;

/**
 * @author Emmanuel
 * 
 */
public class CodeBreakerLauncher {

	private final static int MAX_GUESS_VALUE = 9999;
	private final static int MIN_GUESS_VALUE = 1000;
	private final CodeBreaker game;

	private CodeBreakerLauncher(String secreteCode) throws GameException {
		this.game = new CodeBreaker(secreteCode);
	}

	private void launch() {
		try (Scanner reader = new Scanner(System.in)) {
			while (true) {
				System.out.println(Messages.ENTER_A_FOUR_DIGIT_NUMBER);

				try {
					String number = reader.nextLine();
					ResponseFormatter response = game.playWith(number);
					System.out.println(response.getMessage());
					if (response.matches()) {
						System.out.println(Messages.CONGRATULATIONS);
						break;
					}
				} catch (GuessException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}

	private static String getSecretCode(String[] args) {
		String secreteCode;
		if (args.length == 0)
			secreteCode = Integer.toString(new Random().nextInt(MAX_GUESS_VALUE - MIN_GUESS_VALUE + 1) + MIN_GUESS_VALUE);
		else
			secreteCode = args[0];
		return secreteCode;
	}

	public static void main(String[] args) {
		try {
			System.out.println(Messages.INITIALIZING_GAME);
			String secreteCode = getSecretCode(args);
			new CodeBreakerLauncher(secreteCode).launch();
		} catch (GameException e) {
			System.out.println(e.getMessage());
		}
	}
}
