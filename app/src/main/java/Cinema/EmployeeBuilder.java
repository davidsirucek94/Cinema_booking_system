package Cinema;

import java.time.LocalDate;

public class EmployeeBuilder {

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
	
	public EmployeeBuilder setId(int id) {
		this.id = id;
		return this;
	}
	
	public EmployeeBuilder setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}
	
	public EmployeeBuilder setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}
	
	public EmployeeBuilder setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
		return this;
	}
	
	public EmployeeBuilder setPosition(WorkPositions position) {
		this.position = position;
		return this;
	}
	
	public EmployeeBuilder setSalary(double salary) {
		this.salary = salary;
		return this;
	}
	
	public EmployeeBuilder setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}
	
	public EmployeeBuilder setEmail(String email) {
		this.email = email;
		return this;
	}
	
	public EmployeeBuilder setAdress(String adress) {
		this.adress = adress;
		return this;
	}
	
	public EmployeeBuilder setLogin(String login) {
		this.login = login;
		return this;
	}
	
	public EmployeeBuilder setPassword(String password) {
		this.password = password;
		return this;
	}
	
	public Employee build() {
		return new Employee(id, firstName, lastName, salary, dateOfBirth, position, phoneNumber, email, adress, login, password);
	}

}
