package services;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import beans.Customer;
import beans.Item;
import beans.Order;
import beans.OrderStatus;
import beans.Restaurant;
import beans.ShoppingCart;
import beans.User;
import dao.Customers;
import dao.Orders;

public class OrderService {
	private Orders orders = new Orders();
	private Customers customers = new Customers();
	
	public Collection<Order> getOrders() throws JsonGenerationException, JsonMappingException, IOException {
		return orders.load();
	}
	
	public void createOrder(User user) throws JsonGenerationException, JsonMappingException, IOException {
		ArrayList<Customer> customerList = customers.load();
		Customer customer = new Customer();
		ShoppingCart shoppingCart = new ShoppingCart();
		for(int i = 0; i < customerList.size(); i++) {
			if(user.getUsername().equals(customerList.get(i).getUsername())) {
				customer = customerList.get(i);
				shoppingCart = customer.getShoppingCart();
				customerList.remove(i);
			}
		}
		Order newOrder = new Order(generateId(),
				shoppingCart.getItems(),
				getRestaurant(shoppingCart),
				getRestaurantLogo(shoppingCart),
				new Date(),
				shoppingCart.getPrice(),
				customer.getUsername(),
				OrderStatus.processing);
		orders.save(newOrder);
		customer.setShoppingCart(new ShoppingCart());
		customer.getOrders().add(newOrder);
		customer.setPoints(setPoints(newOrder.getPrice(), customer.getPoints()));
		customerList.add(customer);
		customers.emptyFile();
		for(Customer c : customerList) {
			customers.save(c);
		}
	}
	
	private String generateId() {
		String id = RandomStringUtils.randomAlphanumeric(10);
		return id;
	}
	
	private String getRestaurant(ShoppingCart shoppingCart) {
		Restaurant restaurant = new Restaurant();
		ArrayList<Item> itemList = shoppingCart.getItems();
		restaurant = itemList.get(0).getRestaurant();
		return restaurant.getName();
	}
	
	private String getRestaurantLogo(ShoppingCart shoppingCart) {
		Restaurant restaurant = new Restaurant();
		ArrayList<Item> itemList = shoppingCart.getItems();
		restaurant = itemList.get(0).getRestaurant();
		return restaurant.getImgPath();
	}
	
	public boolean isCartEmpty(User user) throws JsonGenerationException, JsonMappingException, IOException {
		ArrayList<Customer> customerList = customers.load();
		ShoppingCart shoppingCart = new ShoppingCart();
		for(int i = 0; i < customerList.size(); i++) {
			if(user.getUsername().equals(customerList.get(i).getUsername())) {
				shoppingCart = customerList.get(i).getShoppingCart();
			}
		}
		return shoppingCart.getItems().isEmpty();
	}
	
	private double setPoints(double price, double oldPoints) {
		return oldPoints + (price/1000*133);
	}
	
	public ArrayList<Order> getCustomerOrders(User user) throws JsonGenerationException, JsonMappingException, IOException {
		ArrayList<Customer> customerList = customers.load();
		Customer customer = new Customer();
		for(int i = 0; i < customerList.size(); i++) {
			if(customerList.get(i).getUsername().equals(user.getUsername())) {
				customer = customerList.get(i);
			}
		}
		
		return customer.getOrders();
	}
	
	
}
