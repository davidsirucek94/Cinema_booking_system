package Cinema;

public class OrderItem { //parent

	private int id;
	private String description;
	private double price;
	
	public OrderItem(int id, String description, double price) {
		this.id = id;
		this.description = description;
		this.price = price;
	}
	
	public String getName() {
		return description;
	}
	
	public double getPrice() {
		return price;
	}
	
	@Override
	public String toString() {
		return String.format("{id=%d, description=%s, price=%.2f}", id, description, price);
	}
}
