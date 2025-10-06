package Cinema.Food_ordering_system;

import java.util.List;
import java.util.Scanner;

import Cinema.UserInputMethods;

public class FoodOrderingMachine {

	private MachineState state = MachineState.IDLE;

	public void printWelcomeScreen(Scanner scanner) {
		System.out.println("Welcome and order some food!");
		boolean choice = UserInputMethods.getEnterInput(scanner, "Do you want to start ordering?");
		if (choice == true) {
			state = MachineState.IN_USE;
		} else {
			printWelcomeScreen(scanner);
		}
	}

	public void printOrderingScreen(List<Meal> meals, List<Menu> menus, Scanner scanner) {
		Order order = new Order();
		
		int itemNumber = 1;
		for (int i = 0; i < meals.size(); i++) {
			System.out.printf("%d) Meal Name: %s, Meal Price: %.2f", itemNumber, meals.get(i).getName(),
					meals.get(i).getPrice());
			itemNumber++;
		}
		for (int i = 0; i < menus.size(); i++) {
			System.out.printf("%d) Menu Name: %s, Menu Price: %.2f", itemNumber, menus.get(i).getName(),
					menus.get(i).getPrice());
			itemNumber++;
		}
		int choiceIndex = UserInputMethods.getIntegerByChoiceMinusOne(scanner,
				"Choose your meal/menu. Type in the number of your choice:", itemNumber - 1);
		if (choiceIndex < meals.size()) {
			order.addItem(meals.get(choiceIndex));
		} else {
			order.addItem(menus.get(choiceIndex - meals.size()));
		}
	}
}
