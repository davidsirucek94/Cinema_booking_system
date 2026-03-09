package Cinema.Food_ordering_system;

import java.util.Optional;

import Cinema.OrderItem;

public class Meal extends OrderItem {
	
	public Meal(int id, String name, double price, Optional<Integer> discountPercents) {
		super(id, name, price, discountPercents);
	}
	
	public Meal(int id, String name, double price) {
		super(id, name, price);
	}
	
}
