package Cinema;

import java.util.Optional;

public class OrderItem { //parent

	protected int id;
	protected String description;
	protected double price;
	private Optional<Integer> discountPercents;
	
	public OrderItem(int id, String description, double price, Optional<Integer> discountPercents) {
		this.id = id;
		this.description = description;
		this.price = price;
		this.discountPercents = discountPercents;
	}

	public OrderItem(int id, String description, double price) {
		this(id, description, price, Optional.empty());
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return description;
	}
	
	public double getPrice() {
		return price;
	}
	
	public Optional<Integer> getDiscountPercents() {
		return discountPercents;
	}
	
	@Override
	public String toString() {
		return String.format("{id=%d, description=%s, price=%.2f}", id, description, price);
	}
}
