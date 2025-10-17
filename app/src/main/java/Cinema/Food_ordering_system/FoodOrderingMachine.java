package Cinema.Food_ordering_system;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Cinema.UserInputMethods;

public class FoodOrderingMachine {

	private Order order;

	public void printWelcomeScreen(List<Meal> meals, List<Menu> menus, Scanner scanner) {
		System.out.println("Welcome and order some food!");
		boolean choice = UserInputMethods.getEnterInput(scanner, "Do you want to start ordering?");
		if (choice == true) {
			printOrderingScreen(meals, menus, scanner);
		} else {
			printWelcomeScreen(meals, menus, scanner);
		}
	}

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		List<Meal> meals = new ArrayList<>();
		List<Menu> menus = new ArrayList<>();

		Meal meal1 = new Meal(1, "nachos", 200);
		Meal meal2 = new Meal(2, "hotdog", 300);
		meals.add(meal1);
		meals.add(meal2);

		Menu menu1 = new Menu(1, "menuA", 500);
		Menu menu2 = new Menu(2, "menuB", 700);

		menus.add(menu1);
		menus.add(menu2);

		FoodOrderingMachine machine = new FoodOrderingMachine();
		machine.printWelcomeScreen(meals, menus, scanner);

	}

	public void printPaymentScreen(List<Meal> meals, List<Menu> menus, Scanner scanner) {
		System.out.println("---------Payment---------");
		System.out.println("List of purchased items:");
		for (int i = 0; i < order.getItems().size(); i++) {
			OrderItem item = order.getItems().get(i);
			System.out.printf("%d) %s ... %.2f Kč\n", i + 1, item.getName(), item.getPrice());
		}
		System.out.println();
		System.out.printf("Your total price is %.2f Kč\n", order.getTotalPrice());
		new PaymentSystem().initializePayment(scanner);
		System.out.println();
		System.out.println("Thank you for your purchase.");
		
		try {
			Thread.sleep(3000);
			clearScreen();
			printWelcomeScreen(meals, menus, scanner);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void printCancelingScreen() {
		System.out.printf("Thank you for wasting my time... FOOL!");
	}
	
	public void clearScreen() {
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}

	public void printOrderingScreen(List<Meal> meals, List<Menu> menus, Scanner scanner) {

		order = new Order();

		while (true) {
			List<String> options = new ArrayList<>();

			int itemNumber = 1;
			for (int i = 0; i < meals.size(); i++) {
				System.out.printf("%d) Meal Name: %s, Meal Price: %.2f", itemNumber, meals.get(i).getName(),
						meals.get(i).getPrice());
				System.out.println();
				options.add(String.valueOf(itemNumber));
				itemNumber++;
			}
			System.out.println();

			for (int i = 0; i < menus.size(); i++) {
				System.out.printf("%d) Menu Name: %s, Menu Price: %.2f", itemNumber, menus.get(i).getName(),
						menus.get(i).getPrice());
				System.out.println();
				options.add(String.valueOf(itemNumber));
				itemNumber++;
			}
			System.out.println();

			System.out.println("For paying, please type P.");
			options.add("P");

			System.out.println("For canceling your order, please type C.");
			options.add("C");

			System.out.println();

			String stringChoice = UserInputMethods.getStringChoice(scanner, "Type your choice:", options);
			if (stringChoice.equals("P")) {
				printPaymentScreen(meals, menus, scanner);
				break;
			} else if (stringChoice.equals("C")) {
				printCancelingScreen();
				break;
			} else {
				int choiceIndex = Integer.parseInt(stringChoice) - 1;
				if (choiceIndex < meals.size()) {
					order.addItem(meals.get(choiceIndex));
					System.out.println("Your chosen item was added to your order.");
				} else {
					order.addItem(menus.get(choiceIndex - meals.size()));
					System.out.println("Your chosen item was added to your order.");
				}
			}
		}
	}
}
