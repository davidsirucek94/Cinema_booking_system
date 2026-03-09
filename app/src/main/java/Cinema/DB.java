package Cinema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Function;

import Cinema.Food_ordering_system.Meal;
import Cinema.Food_ordering_system.Menu;

public class DB {

	public static ResultSet executeQuery(String query, Object... params) {

		try {
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/kino", "david",
					"david123");
			PreparedStatement statement = connection.prepareStatement(query);
			for (int i = 1; i <= params.length; i++) {
				Object param = params[i - 1];
				switch (param) {
				case String string:
					statement.setString(i, string);
					break;
				case Integer integer:
					statement.setInt(i, integer);
					break;
				case Double doubl:
					statement.setDouble(i, doubl);
					break;
				default:
					throw new RuntimeException(String.format("Type %s is not supported.", param.getClass()));
				}
			}
			ResultSet resultSet = statement.executeQuery();
			return resultSet;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	/*
	 * 
	 * public static ResultSet executeQuery(String query, String login, String
	 * password) {
	 * 
	 * try { Connection connection =
	 * DriverManager.getConnection("jdbc:postgresql://localhost:5432/kino", "david",
	 * "david123"); PreparedStatement statement =
	 * connection.prepareStatement(query); statement.setString(1, login);
	 * statement.setString(2, password); ResultSet resultSet =
	 * statement.executeQuery(); return resultSet; } catch (SQLException e) {
	 * e.printStackTrace(); } return null;
	 * 
	 * }
	 * 
	 * 
	 * public static <T> List<T> executeQuery1(String query, Function<ResultSet, T>
	 * itemMapper) {
	 * 
	 * try { Connection connection =
	 * DriverManager.getConnection("jdbc:postgresql://localhost:5432/kino", "david",
	 * "david123"); Statement statement = connection.createStatement(); ResultSet
	 * resultSet = statement.executeQuery(query);
	 * 
	 * List<T> list = new ArrayList<>(); while (resultSet.next()) {
	 * list.add(itemMapper.apply(resultSet)); } return list;
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } return null;
	 * 
	 * }
	 */

	public static List<Employee> getListOfEmployees() {
		List<Employee> employees = new ArrayList<>();

		ResultSet result = DB.executeQuery("select * from employees order by id");

		try {
			while (result.next()) {
				employees.add(resultSetToEmployee(result));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employees;
	}

	public static List<Meal> getListOfMeals(Optional<Integer> id) {
		List<Meal> meals = new ArrayList<>();

		ResultSet result;

		if (id.isPresent()) {
			result = DB.executeQuery("select * from meals where id=?", id.get());
		} else {
			result = DB.executeQuery("select * from meals order by id");
		}
		
		try {
			while (result.next()) {
				meals.add(new Meal(result.getInt("id"), result.getString("name"), result.getDouble("price"),
						Optional.ofNullable(result.getInt("discount_percents"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return meals;
	}

	public static List<Menu> getListOfMenus() {
		List<Menu> menus = new ArrayList<>();

		ResultSet result = DB.executeQuery("""
				select
					menus.id as menu_id,
				    menus.name as menu_name,
				    menus.price as menu_price,
				    menus.discount_percents as menu_discount,
				    meals.id as meal_id,
				    meals.name as meal_name,
				    meals.price as meal_price,
				    meals.discount_percents as meal_discount
				from menus
				left join menu_meals on menus.id = menu_meals.menu_id
				left join meals on meals.id = menu_meals.meal_id
										""");

		Map<Integer, Menu> mapMenus = new HashMap<>();

		try {
			while (result.next()) {
				Menu menu;
				if (!mapMenus.containsKey(result.getInt("menu_id"))) {
					menu = new Menu(result.getInt("menu_id"), result.getString("menu_name"),
							result.getDouble("menu_price"), Optional.ofNullable(result.getInt("menu_discount")));
					mapMenus.put(result.getInt("menu_id"), menu);
				} else {
					menu = mapMenus.get(result.getInt("menu_id"));
				}

				menu.addItem(new Meal(result.getInt("meal_id"), result.getString("meal_name"),
						result.getDouble("meal_price"), Optional.ofNullable(result.getInt("meal_discount"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		menus.addAll(mapMenus.values());
		// System.out.println(menus);
		return menus;
	}

	public static Employee resultSetToEmployee(ResultSet result) {
		Employee employee = null;
		try {
			employee = new EmployeeBuilder().setId(result.getInt("id")).setFirstName(result.getString("name"))
					.setLastName(result.getString("last_name"))
					.setDateOfBirth(result.getDate("date_of_birth").toLocalDate())
					.setPosition(WorkPositions.valueOf(result.getString("position")))
					.setSalary(result.getDouble("salary")).setPhoneNumber(result.getString("phone_number"))
					.setEmail(result.getString("email")).setAdress(result.getString("adress"))
					.setLogin(result.getString("login")).setPassword(result.getString("password_hash")).build();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}
}
