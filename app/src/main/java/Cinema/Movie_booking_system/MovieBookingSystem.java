package Cinema.Movie_booking_system;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Cinema.UserInputMethods;

public class MovieBookingSystem {

	private Movie movie;

	public void printWelcomeScreen(Scanner scanner, List<Movie> movies, Map<Movie, List<Projection>> projectionsPerMovie) {
		System.out.println("Welcome and let`s book a movie!");
		boolean choice = UserInputMethods.getEnterInput(scanner, "Do you want to start booking?");
		if (choice == true) {
			printOrderingScreen(scanner, movies, projectionsPerMovie);
		} else {
			printWelcomeScreen(scanner, movies, projectionsPerMovie);
		}
	}

	public void printOrderingScreen(Scanner scanner, List<Movie> movies, Map<Movie, List<Projection>> projectionsPerMovie) {

		List<String> options = new ArrayList<>();
		int itemNumber = 1;
		for (int i = 0; i < movies.size(); i++) {
			System.out.printf("%d) Movie Name: %s, Movie Length: %d min, Genre: %s", itemNumber,
					movies.get(i).getName(), movies.get(i).getLength(), movies.get(i).getGenre());
			System.out.println();
			options.add(String.valueOf(itemNumber));
			itemNumber++;
		}

		System.out.println();

		System.out.println("For paying, please type P.");
		options.add("P");

		System.out.println("For canceling your order, please type C.");
		options.add("C");

		System.out.println();

		String stringChoice = UserInputMethods.getStringChoice(scanner, "Type your choice:", options);
		if (stringChoice.equals("P")) {
			printPaymentScreen(); // TODO
			// break;
		} else if (stringChoice.equals("C")) {
			printCancelingScreen();
			// break;
		} else {
			int choiceIndex = Integer.parseInt(stringChoice) - 1;
			Movie chosenMovie = movies.get(choiceIndex);
			printSelectedMovieScreen(chosenMovie, projectionsPerMovie);
		}

	}

	public void printSelectedMovieScreen(Movie chosenMovie, Map<Movie, List<Projection>> projectionsPerMovie) {
		System.out.println(chosenMovie.getDescription());
		List<Projection> projections = projectionsPerMovie.get(chosenMovie);
		//TODO vypsat projekce
	}

	public void printCancelingScreen() {
		System.out.printf("Thank you for wasting my time... FOOL!");
	}

	public void printPaymentScreen() {

	}

	public static void main(String[] args) {

		List<Movie> movies = new ArrayList<>();

		Movie movie1 = new Movie("Shrek", 120, "", MovieGenre.CARTOON);
		Movie movie2 = new Movie("Avengers", 180, "", MovieGenre.SCI_FI);
		Movie movie3 = new Movie("Smile", 150, "", MovieGenre.HORROR);
		movies.add(movie1);
		movies.add(movie2);
		movies.add(movie1);

		Projection projection1 = new Projection(movie1, 200.00, LocalDateTime.parse("2026-06-05T20:00:00"));
		Projection projection2 = new Projection(movie1, 200.00, LocalDateTime.parse("2026-06-05T21:00:00"));

		Projection projection3 = new Projection(movie2, 250.00, LocalDateTime.parse("2026-06-06T19:00:00"));
		Projection projection4 = new Projection(movie2, 250.00, LocalDateTime.parse("2026-06-06T19:30:00"));

		Projection projection5 = new Projection(movie3, 300.00, LocalDateTime.parse("2026-06-07T15:00:00"));
		Projection projection6 = new Projection(movie3, 300.00, LocalDateTime.parse("2026-06-07T17:30:00"));

		Map<Movie, List<Projection>> projectionsPerMovie = Map.of(movie1, List.of(projection1, projection2), movie2,
				List.of(projection3, projection4), movie3, List.of(projection5, projection6));

	}

}
