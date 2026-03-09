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

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

public class TestLoginSystem2 {

	public static void main(String[] args) throws SQLException {

		Scanner scanner = new Scanner(System.in);

		// System.out.println(LoginSystem.loginScreen());
		// ResultSet result = DB.executeQuery("select * from employees where id=? and
		// password_hash=?", 1, "ahoj");
		//String string = DB.getListOfMenus().toString();
		
		String string = "";
		List<Meal> meals = DB.getListOfMeals(Optional.empty());
		for (int i = 0; i < meals.size(); i++) {
			Meal meal = meals.get(i);
			string = string + meal.getId() + "," + meal.getName() + "," + meal.getPrice() + "," + meal.getDiscountPercents() .orElse(null)+ "\n";
		}
		System.out.println(string);
	}

}
