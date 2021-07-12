package beans;

import java.util.ArrayList;
import java.util.Date;

public class Customer extends User{
	private ArrayList<Order> orders;
	private ShoppingCart shoppingCart;
	private int points;
	private CustomerType customerType;
	private boolean deleted;
	
	public Customer() {
		super();
	}

	public Customer(String username, String password, String name, String surname, Gender gender, Date date,
			Role role, boolean isBlocked, ArrayList<Order> orders, ShoppingCart shoppingCart, int points, CustomerType customerType, boolean deleted) {
		super(username, password, name, surname, gender, date, role, isBlocked);
		this.orders = orders;
		this.shoppingCart = shoppingCart;
		this.points = points;
		this.customerType = customerType;
		this.deleted = deleted;
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

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
}
