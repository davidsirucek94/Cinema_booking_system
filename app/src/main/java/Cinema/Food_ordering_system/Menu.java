package Cinema.Food_ordering_system;

import java.util.ArrayList;
import java.util.List;

public class Menu extends OrderItem {
	
	public Menu(int id, String name, double price) {
		super(id, name, price);
	}

	private List<Meal> meals = new ArrayList<>();
	
	public void addItem(Meal meal) {
		meals.add(meal);
	}
	
}
