package Cinema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
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
		
		try {
			Thread.sleep(3000);
			clearScreen();
			printWelcomeScreen();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void storeOrder() {
		try {
			 Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/cinema", "david", "david123");
		     String query = "insert into orders(number, creation_date_time) values(?, ?)";
		     PreparedStatement statement = connection.prepareStatement(query);
		     statement.setInt(1, order.getNumber());
		     statement.setTimestamp(2, Timestamp.valueOf(order.dateTime));
		     statement.execute();
		     
		     Statement statement2 = connection.createStatement();
		     ResultSet resultSetMeals = statement2.executeQuery("select max(id) from orders");
		     int OrderID = resultSetMeals.getInt(1);
		     
		     String query2 = "insert into order_items(description, price, order_id) values (?, ?, ?)";
		     PreparedStatement statement3 = connection.prepareStatement(query2);
		     for (int i = 0; i < order.getItems().size(); i++) {
			     statement3.setString(1, order.getItems().get(i).getName());
			     statement3.setDouble(2, order.getItems().get(i).getPrice());
			     statement3.setInt(3, OrderID);
			     statement3.execute();
		     }

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
