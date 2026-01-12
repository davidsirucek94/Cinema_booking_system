package Cinema;

public class Employee {

	private String name;
	private int age;
	private WorkPositions position;
	private String email;
	private String phoneNumber;
	private String login;
	private String password;
	
	public Employee(String name, int age, WorkPositions position, String email, String phoneNumber, String login, String password) {
		this.age = age;
		this.email = email;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.position = position;
		this.login = login;
		this.password = password;
	}
	
	public String getLogin() {
		return login;
	}
	
	public String getPassword() {
		return password;
	}
	
}
