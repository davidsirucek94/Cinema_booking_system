package Cinema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Cinema.Food_ordering_system.FoodOrderingSystem;
import Cinema.Food_ordering_system.Meal;
import Cinema.Food_ordering_system.Menu;

public class Sandbox {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		try {
	        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/cinema", "david", "david123");
	        Statement statement = connection.createStatement();
	        ResultSet resultSetMeals = statement.executeQuery("select * from meals");
	        List<Meal> mealsList = new ArrayList<>();
	        Map<Integer, Meal> mealsMap = new HashMap<>();
	        while (resultSetMeals.next()) {
	        	int id = resultSetMeals.getInt("id");
	        	String name = resultSetMeals.getString("name");
	        	double price = resultSetMeals.getDouble("price");
	        	Meal meal = new Meal(id, name, price);
	        	mealsList.add(meal);
	        	mealsMap.put(id, meal);
	        }
	       
	        ResultSet resultSetMenus = statement.executeQuery("select * from menus");
	        List<Menu> menusList = new ArrayList<>();
	        Map<Integer, Menu> menusMap = new HashMap<>(); 
	        while (resultSetMenus.next()) {
	        	int id = resultSetMenus.getInt("id");
	        	String name = resultSetMenus.getString("name");
	        	double price = resultSetMenus.getDouble("price");
	        	Menu menu = new Menu(id, name, price);
	        	menusList.add(menu);
	        	menusMap.put(id, menu);
	        }
	        
	        ResultSet resultSetMenuMeals = statement.executeQuery("select * from menu_meals");
	        while (resultSetMenuMeals.next()) {
	        	int menuId = resultSetMenuMeals.getInt("menu_id");
	        	int mealId = resultSetMenuMeals.getInt("meal_id");
	        	Menu menu = menusMap.get(menuId);
	        	Meal meal = mealsMap.get(mealId);
	        	menu.addItem(meal);
	        }
	       
	        FoodOrderingSystem foodSystem = new FoodOrderingSystem(scanner, mealsList, menusList);
	        
	        System.out.println(mealsMap);

	        System.out.println(menusMap);

	        } catch(Exception E) {
	        	E.printStackTrace();
	        }
	}

}
