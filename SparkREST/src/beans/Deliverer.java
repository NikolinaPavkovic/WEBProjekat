package beans;

import java.sql.Date;
import java.util.ArrayList;

public class Deliverer extends User{
	private ArrayList<Order> orders;

	public Deliverer() {
		super();
	}
	
	public Deliverer(String username, String password, String name, String surname, Gender gender, Date dateOfBirth,
			Role role, ArrayList<Order> orders) {
		super(username, password, name, surname, gender, dateOfBirth, role);
		this.orders = orders;
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}
	
	
	
	
}
