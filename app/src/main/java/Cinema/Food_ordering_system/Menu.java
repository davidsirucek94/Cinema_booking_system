package Cinema.Food_ordering_system;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Cinema.OrderItem;

public class Menu extends OrderItem {
	
	public Menu(int id, String name, double price, Optional<Integer> discountPercents) {
		super(id, name, price, discountPercents);
	}

	private List<Meal> meals = new ArrayList<>();
	
	public void addItem(Meal meal) {
		meals.add(meal);
	}
	
	@Override
	public String toString() {
		return String.format("{id=%d, description=%s, price=%.2f, meals=%s}", id, description, price, meals.toString());
	}
}
