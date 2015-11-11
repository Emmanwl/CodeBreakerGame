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
public class CodeBreakerGameLauncher {

	private final CodeBreakerGame game;

	private CodeBreakerGameLauncher(String secreteCode) throws GameException {
		this.game = new CodeBreakerGame(secreteCode);
	}

	private void launch() {
		Scanner reader = new Scanner(System.in);
		try {
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
		} finally {
			reader.close();
		}
	}

	private static String getSecretCode(String[] args) {
		String secreteCode;
		if (args.length == 0)
			secreteCode = Integer.toString(new Random().nextInt(9999 - 1000 + 1) + 1000);
		else
			secreteCode = args[0];
		return secreteCode;
	}

	public static void main(String[] args) {
		try {
			System.out.println(Messages.INITIALIZING_GAME);
			String secreteCode = getSecretCode(args);
			new CodeBreakerGameLauncher(secreteCode).launch();
		} catch (GameException e) {
			System.out.println(e.getMessage());
		}
	}
}
