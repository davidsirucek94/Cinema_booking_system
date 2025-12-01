package Cinema;

import java.util.Scanner;

public abstract class OrderingSystem {

	protected Scanner scanner;
	protected Order order;

	public OrderingSystem(Scanner scanner) {
		this.scanner = scanner;
	}

	public abstract String getGreeting();
	
	public void printWelcomeScreen() {
		System.out.println(getGreeting());
		boolean choice = UserInputMethods.getEnterInput(scanner, "Do you wish to proceed?");
		if (choice == true) {
			printOrderingScreen();
		} else {
			printWelcomeScreen();
		}
	}

	public abstract void printOrderingScreen();

	public void printCancelingScreen() {
		System.out.printf("Thank you for wasting my time... FOOL!");
		printWelcomeScreen();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void clearScreen() {
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}

	public final void printPaymentScreen() {
		if (order == null) {
			throw new RuntimeException("You have to create an order!");
		}
		System.out.println("---------Payment---------");
		System.out.println("List of purchased items:");
		for (int i = 0; i < order.getItems().size(); i++) {
			OrderItem item = order.getItems().get(i);
			System.out.printf("%d) %s ... %.2f Kč\n", i + 1, item.getName(), item.getPrice());
		}
		System.out.println();
		System.out.printf("Your total price is %.2f Kč\n", order.getTotalPrice());
		new PaymentSystem().initializePayment(scanner, order.getTotalPrice());
		System.out.println();
		System.out.println("Thank you for your purchase.");
		/*
		try {
			Thread.sleep(3000);
			clearScreen();
			printWelcomeScreen();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/
	}
}
