package Cinema;

public enum WorkPositions {

	MANAGER("Manager"), OWNER("Owner"), CLEANING_LADY("Cleaner"), CASHIER("Cashier"); //tohle jsou instance
	
	private String label;
	
	private WorkPositions(String label) { //Enum může mít jen privátní konstruktor
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
}
