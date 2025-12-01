package Cinema.Movie_booking_system;

public class Room {

	int roomNumber;
	int numberOfRows;
	int numberOfColumns;
	boolean isVip;
	boolean isImax;
	
	public Room(int roomNumber, int numberOfRows, int numberOfColumns, boolean isVip, boolean isImax) {
		this.roomNumber = roomNumber;
		this.numberOfRows = numberOfRows;
		this.numberOfColumns = numberOfColumns;
		this.isVip = isVip;
		this.isImax = isImax;
	}
	
	public int getRows() {
		return numberOfRows;
	}
	
	public int getColumns() {
		return numberOfColumns;
	}
	
	public int getRoomNumber() {
		return roomNumber;
	}
}
