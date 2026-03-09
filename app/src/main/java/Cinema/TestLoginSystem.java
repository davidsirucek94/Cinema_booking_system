package Cinema;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import com.sun.net.httpserver.HttpServer;

import Cinema.Food_ordering_system.Meal;
import Cinema.Food_ordering_system.Menu;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

public class TestLoginSystem {

	public static void main(String[] args) throws SQLException {

		Scanner scanner = new Scanner(System.in);

		// System.out.println(LoginSystem.loginScreen());
		// ResultSet result = DB.executeQuery("select * from employees where id=? and
		// password_hash=?", 1, "ahoj");
		// String string = DB.getListOfMenus().toString();

		HttpServer server;
		try {
			server = HttpServer.create(new InetSocketAddress(8080), 0);
			// obsluha cesty "/"
			server.createContext("/meals", new MealsHandler());
			server.createContext("/menus", new MenusHandler());
			server.setExecutor(null); // default executor
			server.start();

			Runtime.getRuntime().addShutdownHook(new Thread(() -> {
				System.out.println("Vypínám server...");
				server.stop(0);
			}));

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Server běží na http://localhost:8080");
	}

	static class MealsHandler implements HttpHandler {
		@Override
		public void handle(HttpExchange exchange) throws IOException {
			exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
			exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
			exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

			String query = exchange.getRequestURI().getQuery();
			System.out.println(query);

			String[] symbols = query.split("=");
			String number = symbols[1];
			int numberI = Integer.parseInt(number);
			System.out.println(numberI);

			String response = "";
			List<Meal> meals = DB.getListOfMeals(Optional.of(numberI));			
			// System.out.println(meals);
			for (int i = 0; i < meals.size(); i++) {
				Meal meal = meals.get(i);
				response = response + meal.getId() + "," + meal.getName() + "," + meal.getPrice() + ","
						+ meal.getDiscountPercents().orElse(null);
				if (i < meals.size() - 1) {
					response = response + "\n";
				}

			}
			// System.out.println(response);
			exchange.sendResponseHeaders(200, response.getBytes().length);

			try (OutputStream os = exchange.getResponseBody()) {
				os.write(response.getBytes());
			}
		}
	}

	static class MenusHandler implements HttpHandler {
		@Override
		public void handle(HttpExchange exchange) throws IOException {
			exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
			exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
			exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

			String response = "";
			List<Menu> menus = DB.getListOfMenus();
			// System.out.println(menus);
			for (int i = 0; i < menus.size(); i++) {
				Menu menu = menus.get(i);
				response = response + menu.getId() + "," + menu.getName() + "," + menu.getPrice() + ","
						+ menu.getDiscountPercents().orElse(null);
				if (i < menus.size() - 1) {
					response = response + "\n";
				}

			}
			// System.out.println(response);
			exchange.sendResponseHeaders(200, response.getBytes().length);

			try (OutputStream os = exchange.getResponseBody()) {
				os.write(response.getBytes());
			}
		}
	}
}
