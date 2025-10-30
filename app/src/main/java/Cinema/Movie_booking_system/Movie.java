package Cinema.Movie_booking_system;

public class Movie {

	private String name;
	private int lengthInMin;
	private String description;
	private MovieGenre genre;
	
	public Movie(String name, int lengthInMin, String description, MovieGenre genre) {
		this.name = name;
		this.lengthInMin = lengthInMin;
		this.description = description;
		this.genre = genre;
	}
	
	public String getName() {
		return name;
	}
	
	public int getLength() {
		return lengthInMin;
	}
	
	public MovieGenre getGenre() {
		return genre;
	}
	
	public String getDescription() {
		return description;
	}
}
