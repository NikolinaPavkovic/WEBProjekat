package beans;

import java.sql.Date;
import java.util.ArrayList;

public class Customer extends User{
	private ArrayList<Order> orders;
	private ShoppingCart shoppingCart;
	private int points;
	private CustomerType customerType;
	
	public Customer() {
		super();
	}

	public Customer(String username, String password, String name, String surname, Gender gender, Date dateOfBirth,
			Role role, ArrayList<Order> orders, ShoppingCart shoppingCart, int points, CustomerType customerType) {
		super(username, password, name, surname, gender, dateOfBirth, role);
		this.orders = orders;
		this.shoppingCart = shoppingCart;
		this.points = points;
		this.customerType = customerType;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}
	
	
}
