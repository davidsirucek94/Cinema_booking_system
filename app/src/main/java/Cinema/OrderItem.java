package Cinema;

public class OrderItem { //parent

	private int id;
	private String name;
	private double price;
	
	public OrderItem(int id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	
	public double getPrice() {
		return price;
	}
	
	@Override
	public String toString() {
		return String.format("{id=%d, name=%s, price=%.2f}", id, name, price);
	}
}
