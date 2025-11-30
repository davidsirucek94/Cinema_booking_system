package Cinema.Movie_booking_system;

import java.time.LocalDateTime;
import java.util.Set;

public class Projection {

	private LocalDateTime dateTime;
	private double price;
	private boolean isVip;
	private boolean isImax;
	private Movie movie;
	private Room room;
	private Set<String> bookedSeats;
	
	public Projection(Movie movie, double price, LocalDateTime dateTime, Room room) {
		this(movie, price, dateTime, room, false, false);
	}
	
	public Projection(Movie movie, double price, LocalDateTime dateTime, Room room, boolean isVip, boolean isImax) {
		this.movie = movie;
		this.price = price;
		this.dateTime = dateTime;
		this.isVip = isVip;
		this.isImax = isImax;
		this.room = room;
	}
	
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	
	public Room getRoom() {
		return room;
	}
	
	public double getPrice() {
		return price;
	}
	
	public Set<String> getBookedSeats() {
		return bookedSeats;
	}
	
}
