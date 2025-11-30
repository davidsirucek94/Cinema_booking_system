package Cinema.Movie_booking_system;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import Cinema.Constants;
import Cinema.OrderingSystem;
import Cinema.PaymentSystem;
import Cinema.UserInputMethods;

public class MovieOrderingSystem extends OrderingSystem {

	private List<Movie> movies;
	private Map<Movie, List<Projection>> projectionsPerMovie;
	private Movie chosenMovie;
	private Set<String> chosenSeats;
	private Projection chosenProjection;
	
	public MovieOrderingSystem(Scanner scanner, List<Movie> movies, Map<Movie, List<Projection>> projectionsPerMovie) {
		super(scanner);
		this.movies = movies;
		this.projectionsPerMovie = projectionsPerMovie;
	}

	@Override
	public void printOrderingScreen() {

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

		System.out.println("For canceling your order, please type C.");
		options.add("C");

		System.out.println();

		String stringChoice = UserInputMethods.getStringChoice(scanner, "Type your choice:", options);
		if (stringChoice.equals("C")) {
			printCancelingScreen();
		} else {
			int choiceIndex = Integer.parseInt(stringChoice) - 1;
			chosenMovie = movies.get(choiceIndex);
			printSelectedMovieScreen();
		}
	}

	public void printSelectedMovieScreen() {
		List<String> options = new ArrayList<>();
		int itemNumber = 1;
		System.out.println(chosenMovie.getDescription());
		List<Projection> projections = projectionsPerMovie.get(chosenMovie);
		System.out.println("Choose the projection you want to see:");

		for (int i = 0; i < projections.size(); i++) {
			options.add(String.valueOf(itemNumber));
			System.out.printf("%d) %s, %.2f,- Kč\n", itemNumber,
					projections.get(i).getDateTime().format(Constants.FORMATTER), projections.get(i).getPrice());
			itemNumber++;
		}

		String stringChoice = UserInputMethods.getStringChoice(scanner, "Type your choice:", options);
		int choiceIndex = Integer.parseInt(stringChoice) - 1;
		Projection chosenProjection = projections.get(choiceIndex);
		Room chosenRoom = chosenProjection.getRoom();
		Set<String> chosenSeats = new HashSet<>();

		do {
			List<String> seatOptions = new ArrayList<>();

			for (int i = 0; i < chosenRoom.getRows(); i++) {
				for (int j = 0; j < chosenRoom.getColumns(); j++) {
					String seat = String.format("%dx%d", i + 1, j + 1);
					if (!chosenSeats.contains(seat)) {
						seatOptions.add(seat);
						System.out.printf(seat + " ");
					}
				}
				System.out.println();
			}
			seatOptions.add("D");
			System.out.println("When you`re done, press 'D'");

			String stringSeatChoice = UserInputMethods.getStringChoice(scanner, "Type your choice:", seatOptions);
			if (stringSeatChoice.equals("D")) {
				break;
			} else {
				chosenSeats.add(stringSeatChoice);
			}
		} while (true);

		printPaymentScreen();
	}

	public void printPaymentScreen() { //TODO sjednotit Payment Screens pomocí třídy OrderingSystem
		
		System.out.println("---------Payment---------");
		int numberOfChosenSeats = chosenSeats.size();
		System.out.printf("You have selected %d seats.\n", numberOfChosenSeats);

		String seatsPrint = "Seats selected: ";

		boolean isFirst = true;
		for (String chosenSeat : chosenSeats) {
			if (isFirst) {
				seatsPrint = seatsPrint + chosenSeat;
				isFirst = false;
			} else {
				seatsPrint = seatsPrint + ", " + chosenSeat;
			}
		}

		System.out.println(seatsPrint);

		double totalPrice = chosenProjection.getPrice() * numberOfChosenSeats;
		System.out.printf("Total price of your booking is %.2f,- Kč.\n", totalPrice);

		new PaymentSystem().initializePayment(scanner, totalPrice);
	}

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		List<Movie> movies = new ArrayList<>();

		Movie movie1 = new Movie("Shrek", 120, "", MovieGenre.CARTOON);
		Movie movie2 = new Movie("Avengers", 180, "", MovieGenre.SCI_FI);
		Movie movie3 = new Movie("Smile", 150, "", MovieGenre.HORROR);
		movies.add(movie1);
		movies.add(movie2);
		movies.add(movie3);

		Room room1 = new Room(1, 20, 20, false, false);
		Room room2 = new Room(2, 10, 10, false, false);
		Room room3 = new Room(3, 30, 30, false, false);

		Projection projection1 = new Projection(movie1, 200.00, LocalDateTime.parse("2026-06-05T20:00:00"), room1);
		Projection projection2 = new Projection(movie1, 200.00, LocalDateTime.parse("2026-06-05T21:00:00"), room2);

		Projection projection3 = new Projection(movie2, 250.00, LocalDateTime.parse("2026-06-06T19:00:00"), room2);
		Projection projection4 = new Projection(movie2, 250.00, LocalDateTime.parse("2026-06-06T19:30:00"), room3);

		Projection projection5 = new Projection(movie3, 300.00, LocalDateTime.parse("2026-06-07T15:00:00"), room1);
		Projection projection6 = new Projection(movie3, 300.00, LocalDateTime.parse("2026-06-07T17:30:00"), room3);

		Map<Movie, List<Projection>> projectionsPerMovie = Map.of(movie1, List.of(projection1, projection2), movie2,
				List.of(projection3, projection4), movie3, List.of(projection5, projection6));

		MovieOrderingSystem system = new MovieOrderingSystem(scanner, movies, projectionsPerMovie);
		system.printWelcomeScreen();

	}

}
