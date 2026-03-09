package Cinema.Food_ordering_system;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Cinema.Order;
import Cinema.OrderItem;
import Cinema.OrderingSystem;
import Cinema.PaymentSystem;
import Cinema.UserInputMethods;

public class FoodOrderingSystem extends OrderingSystem {

	private List<Meal> meals;
	private List<Menu> menus;
	
	public FoodOrderingSystem(Scanner scanner, List<Meal> meals, List<Menu> menus) {
		super(scanner);
		this.meals = meals;
		this.menus = menus;
	}
	
	@Override
	public String getGreeting() {
		return "Welcome to the Food Ordering System! Let`s order some food!";
	}
	
	@Override
	public void printOrderingScreen() {

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
				printPaymentScreen();
				order.setCreationDateTime();
				storeOrder();
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
