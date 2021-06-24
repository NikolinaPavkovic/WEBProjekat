package beans;

import java.sql.Date;
import java.util.ArrayList;

public class Order {
	private String id;
	private ArrayList<Item> orderedItems;
	private Restaurant restaurant;
	private Date dateAndTime;
	private double price;
	private Customer customer;
	private OrderStatus status;
	
	public Order() {
		super();
	}

	public Order(String id, ArrayList<Item> orderedItems, Restaurant restaurant, Date dateAndTime, double price,
			Customer customer, OrderStatus status) {
		super();
		this.id = id;
		this.orderedItems = orderedItems;
		this.restaurant = restaurant;
		this.dateAndTime = dateAndTime;
		this.price = price;
		this.customer = customer;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<Item> getOrderedItems() {
		return orderedItems;
	}

	public void setOrderedItems(ArrayList<Item> orderedItems) {
		this.orderedItems = orderedItems;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Date getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	
}
