package Cinema.Food_ordering_system;

import java.util.ArrayList;
import java.util.List;

public class Order {
	
	private int id;
	private int number;
	private List<OrderItem> itemList = new ArrayList<>();
	
	public Order() {
		
	}
	
	public void addItem(OrderItem item) {
		itemList.add(item);
	}
	
	public void deleteItem(OrderItem item) {
		//TODO
	}
	
	public double getTotalPrice() {
		double totalPrice = 0;
		for (OrderItem item : itemList) {
			totalPrice += item.getPrice();
		}
		return totalPrice;
	}

}
