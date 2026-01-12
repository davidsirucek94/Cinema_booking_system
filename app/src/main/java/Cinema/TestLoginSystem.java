package Cinema;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestLoginSystem {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		List<Employee> employees = new ArrayList<>();
		/*
		Employee employee1 = new Employee ("David", 31, WorkPositions.OWNER, "", "", "David", "David123");
		Employee employee2 = new Employee ("Vítek", 20, WorkPositions.MANAGER, "", "", "Vitek", "Vitek123");
		employees.add(employee1);
		employees.add(employee2);
		*/
		
		employees.add(LoginSystem.createNewEmployee(scanner));
		employees.add(LoginSystem.createNewEmployee(scanner));
		LoginSystem.loginScreen(employees);
		
	}

}
