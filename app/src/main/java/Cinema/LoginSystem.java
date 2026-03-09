package Cinema;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoginSystem {

	public static Employee loginScreen() throws SQLException {
		Scanner scanner = new Scanner(System.in);
		Employee user = null;
		do {
			System.out.println("Welcome!");
			String userLogin = UserInputMethods.getNotBlankString(scanner, "Enter login:");
			String userPassword = UserInputMethods.getNotBlankString(scanner, "Enter password:");

			ResultSet result = DB.executeQuery("select * from employees where login=? and password_hash=?", 1);
			
			if (result.next()) {
				return DB.resultSetToEmployee(result);
			}

			System.out.println("This login or password are not correct. Please try again.");

		} while (true);
	}

	public static Employee createNewEmployee(Scanner scanner) {
		int number = 1;
		String firstName = UserInputMethods.getNotBlankString(scanner, "Enter first name:");
		String lastName = UserInputMethods.getNotBlankString(scanner, "Enter last name:");
		double salary = UserInputMethods.getValidDouble(scanner, "Enter salary:");
		LocalDate birthDate = UserInputMethods.getDate(scanner);
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
		String adress = UserInputMethods.getNotBlankString(scanner, "Enter adress:");
		String login = UserInputMethods.getNotBlankString(scanner, "Enter login:");
		String password = UserInputMethods.getNotBlankString(scanner, "Enter password:");
		Employee newEmployee = new EmployeeBuilder().setId(-1).setFirstName(firstName).setLastName(lastName)
				.setSalary(salary).setDateOfBirth(birthDate).setPosition(chosenPosition).setEmail(email)
				.setPhoneNumber(phoneNumber).setAdress(adress).setLogin(login).setPassword(password).build();
		return newEmployee;
	}

}
