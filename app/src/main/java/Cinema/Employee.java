package Cinema;

import java.time.LocalDate;

public class Employee {

	private int id;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private WorkPositions position;
	private double salary;
	private String phoneNumber;
	private String email;
	private String adress;
	private String login;
	private String password;

	public Employee(int id, String firstName, String lastName, double salary, LocalDate dateOfBirth, WorkPositions position,
			String phoneNumber, String email, String adress, String login, String password) {
		this.id = id;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.adress = adress;
		this.position = position;
		this.login = login;
		this.salary = salary;
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

}
