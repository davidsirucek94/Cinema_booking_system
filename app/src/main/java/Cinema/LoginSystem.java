package Cinema;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoginSystem {

	public static void loginScreen(List<Employee> storedEmployees) {
		Scanner scanner = new Scanner(System.in);
		Employee user = null;
		do {
			System.out.println("Welcome!");
			String userLogin = UserInputMethods.getNotBlankString(scanner, "Enter login:");
			String userPassword = UserInputMethods.getNotBlankString(scanner, "Enter password:");

			for (Employee employee : storedEmployees) {
				if (userLogin == employee.getLogin() && userPassword == employee.getPassword()) {
					user = employee;
				} else {
					System.out.println("This login or password are not correct. Please try again.");
				}
			}
		} while (user == null);
	}

	public static Employee createNewEmployee(Scanner scanner) {
		int number = 1;
		String name = UserInputMethods.getNotBlankString(scanner, "Enter name:");
		int age = UserInputMethods.getNumberGreaterThanZero(scanner, "Enter age:");
		WorkPositions[] positions = WorkPositions.values();
		System.out.println("List of available positions:");
		for (WorkPositions position : positions) {
			System.out.printf("%d) %s\n", number, position.getLabel());
			number++;
		}
		int position = UserInputMethods.getIntegerByChoiceMinusOne(scanner,
				"Choose a position. Type in only the number:", positions.length);
		WorkPositions chosenPosition = positions[position];
		String email = UserInputMethods.getCorrectEmailAdress(scanner, "Enter email:");
		System.out.println(email);
		String phoneNumber = UserInputMethods.getNotBlankString(scanner, "Enter phone number:");
		String login = UserInputMethods.getNotBlankString(scanner, "Enter login:");
		String password = UserInputMethods.getNotBlankString(scanner, "Enter password:");
		Employee newEmployee = new Employee(name, age, chosenPosition, email, phoneNumber, login, password);
		return newEmployee;
	}

}
