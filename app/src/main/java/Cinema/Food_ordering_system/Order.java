package Cinema.Food_ordering_system;

import java.util.ArrayList;
import java.util.Collections;
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
	
	public List<OrderItem> getItems() {
		return Collections.unmodifiableList(itemList); //vytváří jiný list, který nejde modifikovat (aby mi nezměnili můj list)
	}
	
	public double getTotalPrice() {
		double totalPrice = 0;
		for (OrderItem item : itemList) {
			totalPrice += item.getPrice();
		}
		return totalPrice;
	}

}
