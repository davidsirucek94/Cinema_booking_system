package Cinema;

import java.util.Scanner;

public abstract class OrderingSystem {
	
	protected Scanner scanner;
	
	public OrderingSystem(Scanner scanner) {
		this.scanner = scanner;
	}
	
	public void printWelcomeScreen() {
		System.out.println("Welcome!");
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
	
	//TODO vyřešit výpis různých hlášek pro screeny
}
