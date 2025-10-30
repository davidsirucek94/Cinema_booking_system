package Cinema.Movie_booking_system;

import java.time.LocalDateTime;

public class Projection {

	private LocalDateTime dateTime;
	private double price;
	private boolean isVip;
	private boolean isImax;
	private Movie movie;
	
	public Projection(Movie movie, double price, LocalDateTime dateTime) {
		this(movie, price, dateTime, false, false);
	}
	
	public Projection(Movie movie, double price, LocalDateTime dateTime, boolean isVip, boolean isImax) {
		this.movie = movie;
		this.price = price;
		this.dateTime = dateTime;
		this.isVip = isVip;
		this.isImax = isImax;
	}
	
}
