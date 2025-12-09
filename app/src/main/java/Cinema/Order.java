package Cinema;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
	
	private int id;
	private int number;
	private List<OrderItem> itemList = new ArrayList<>();
	LocalDateTime dateTime;
	
	public Order() {
		
	}
	
	public void addItem(OrderItem item) {
		itemList.add(item);
	}
	
	public void deleteItem(OrderItem item) {
		//TODO
	}
	
	public int getNumber() {
		return number;
	}
	
	public LocalDateTime getDateTime() {
		return dateTime;
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
	
	public void setCreationDateTime() {
		dateTime = LocalDateTime.now();
	}

}
