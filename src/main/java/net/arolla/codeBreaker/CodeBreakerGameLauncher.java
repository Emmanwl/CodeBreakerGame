package net.arolla.codeBreaker;

import java.util.Scanner;

import net.arolla.codeBreaker.match.ResponseFormatter;

/**
 * @author Emmanuel
 * 
 */
public class CodeBreakerGameLauncher {

	private static final String DEFAULT_SECRET_CODE = "9999";
	
	private final CodeBreakerGame game;

	private CodeBreakerGameLauncher(String secreteCode) {
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
				} catch (NumberFormatException e) {
					System.out.println(e.getMessage());
				}
			}
		} finally {
			reader.close();
		}
	}

	private static String getSecretCode(String[] args) {
		return (args.length > 0 ? args[0] : DEFAULT_SECRET_CODE);
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(Messages.INITIALIZING_GAME);
			String secreteCode = getSecretCode(args);
			new CodeBreakerGameLauncher(secreteCode).launch();
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		}
	}
}
