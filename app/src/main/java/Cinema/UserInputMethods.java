package Cinema;

import java.util.Scanner;

public class UserInputMethods {

	public static int getNumberGreaterThanZero(Scanner scanner, String promptMessage) {
		System.out.println(promptMessage);
		boolean validNumber = true;
		int number;
		do {
			number = Integer.parseInt(scanner.nextLine());
			if (number <= 0) {
				System.out.println("You entered wrong number. Please, enter it again: ");
				validNumber = false;
				continue;
			} else {
				validNumber = true;
			}

		} while (validNumber == false);
		return number;
	}

	public static boolean getBooleanInput(Scanner scanner, String promptMessage) {
		System.out.println(promptMessage + " Enter yes/no: ");
		String question = scanner.nextLine().toLowerCase().trim();
		return question.equals("yes");
	}
	
	public static boolean getEnterInput(Scanner scanner, String promptMessage) {
		System.out.println(promptMessage + " Press ENTER: ");
		String question = scanner.nextLine().toLowerCase().trim();
		return question.equals("\n");
	}

	public static String getNotBlankString(Scanner scanner, String promptMessage) {
		String value;
		do {
			System.out.println(promptMessage);
			value = scanner.nextLine();
			if (value.isBlank()) {
				System.out.println("You entered an empty String. Please enter it again.");
			}
		} while (value.isBlank());
		return value;
	}

	public static double getValidDouble(Scanner scanner, String promptMessage) {
		double value;
		boolean isValid = true;
		do {
			System.out.println(promptMessage);
			try {
				value = Double.parseDouble(scanner.nextLine().replace(",", ".").trim());
				isValid = true; // pokud je hodnota neplatná, na tento řádek ani nedojde (vyskočí výjimka)
			} // zkus to udělat, a když padne výjimka, tak jdi do catch
			catch (Exception E) {
				System.out.println("You have entered an invalid number. Try again.");
				value = -1;
				isValid = false;
			}
		} while (isValid == false);
		return value;
	}

	public static int getIntegerByChoiceMinusOne(Scanner scanner, String promptMessage, int maxNumber) {
		int value;
		boolean isValid = true;
		do {
			value = UserInputMethods.getNumberGreaterThanZero(scanner, promptMessage);
			if (value > maxNumber) {
				System.out.println("You have entered an invalid number. Try again.");
				value = -1;
				isValid = false;
				continue;
			} else {
				isValid = true;
			}

		} while (isValid == false);
		return value - 1;
	}

	public static boolean getAnotherChoice(Scanner scanner, String objectName) {
		System.out.printf("Do you wish to enter another %s? Enter Yes/No.\n", objectName);
		String choice = scanner.nextLine().toLowerCase().trim();
		return choice.equals("yes");
	}
	
}
