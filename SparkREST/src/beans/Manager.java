package beans;

import java.sql.Date;

public class Manager extends User{
	private Restaurant restaurant;

	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Manager(String username, String password, String name, String surname, Gender gender, java.util.Date dateOfBirth,
			Role role,Restaurant restaurant) {
		super(username, password, name, surname, gender, dateOfBirth, role);
		this.restaurant = restaurant;
		// TODO Auto-generated constructor stub
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	
	
}
