package services;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import beans.Customer;
import beans.CustomerType;
import beans.Item;
import beans.Order;
import beans.OrderStatus;
import beans.Restaurant;
import beans.ShoppingCart;
import beans.TypeName;
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
				calculatePriceWithDiscount(customer, shoppingCart.getPrice()),
				customer.getUsername(),
				OrderStatus.processing);
		orders.save(newOrder);
		customer.setShoppingCart(new ShoppingCart());
		customer.getOrders().add(newOrder);
		customer.setPoints(setPoints(newOrder.getPrice(), customer.getPoints()));
		customer.setCustomerType(updateCustomerType(customer.getCustomerType(), customer.getPoints()));
		customerList.add(customer);
		customers.emptyFile();
		for(Customer c : customerList) {
			customers.save(c);
		}
	}
	
	private double calculatePriceWithDiscount(Customer customer, double price) {
		double newPrice = 0.0;
		newPrice = price * ((100.0 - customer.getCustomerType().getDiscount()) / 100);
		return newPrice;
	}
	
	private CustomerType updateCustomerType(CustomerType ct, double points) {
		CustomerType customerType = new CustomerType();
		if(points >= 1500.0 && points < 3000.0) {
			customerType.setTypeName(TypeName.bronze);
			customerType.setDiscount(5.0);
			customerType.setRequiredPoints(3000.0 - points);
		} else if(points >= 3000.0 && points < 4500.0) {
			customerType.setTypeName(TypeName.silver);
			customerType.setDiscount(10.0);
			customerType.setRequiredPoints(4500.0 - points);
		} else if(points >= 4500.0) {
			customerType.setTypeName(TypeName.gold);
			customerType.setDiscount(15);
			customerType.setRequiredPoints(0.0);
		} else {
			customerType.setTypeName(TypeName.steel);
			customerType.setDiscount(0.0);
			customerType.setRequiredPoints(1500.0 - points);
		}
		return customerType;
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
	
	public void cancelOrder(String id) throws JsonGenerationException, JsonMappingException, IOException {
		ArrayList<Order> orderList = orders.load();
		String username = "";
		double price = 0.0;
		for(int i = 0; i < orderList.size(); i++) {
			if(orderList.get(i).getId().equals(id)) {
				username = orderList.get(i).getCustomer();
				price = orderList.get(i).getPrice();
				orderList.remove(i);
			}
		}
		orders.emptyFile();
		for (Order order : orderList) {
			orders.save(order);
		}
		
		deleteOrderFromCustomer(username, id, price);
		
	}
	
	private void deleteOrderFromCustomer(String username, String id, double orderPrice) throws JsonGenerationException, JsonMappingException, IOException {
		ArrayList<Customer> customerList = customers.load();
		Customer customer = new Customer();
		for(int i = 0; i < customerList.size(); i++) {
			if(username.equals(customerList.get(i).getUsername())) {
				customer = customerList.get(i);
				customerList.remove(i);
			}
		}
		
		ArrayList<Order> orderList = customer.getOrders();
		for(int i = 0; i < orderList.size(); i++) {
			if(orderList.get(i).getId().equals(id)) {
				orderList.remove(i);
				break;
			}
		}
		double oldPoints = customer.getPoints();
		customer.setPoints(oldPoints - lostPoints(oldPoints, orderPrice));
		customer.setCustomerType(updateCustomerType(customer.getCustomerType(), customer.getPoints()));
		customer.setOrders(orderList);
		customerList.add(customer);
		customers.emptyFile();
		for (Customer cus : customerList) {
			customers.save(cus);
		}
	}
	
	private double lostPoints(double oldPoints, double price) {
		return price/1000*133*4;
	}
	
	public ArrayList<Order> getCustomerUndeliveredOrders(User user) throws JsonGenerationException, JsonMappingException, IOException {
		ArrayList<Customer> customerList = customers.load();
		ArrayList<Order> undeliveredOrders = new ArrayList<Order>();
		ArrayList<Order> allOrders = new ArrayList<Order>();
		Customer customer = new Customer();
		for(int i = 0; i < customerList.size(); i++) {
			if(customerList.get(i).getUsername().equals(user.getUsername())) {
				customer = customerList.get(i);
				allOrders = customer.getOrders();
			}
		}
		
		for(int i = 0; i < allOrders.size(); i++) {
			if(allOrders.get(i).getStatus() != OrderStatus.canceled && allOrders.get(i).getStatus() != OrderStatus.delivered ) {
				undeliveredOrders.add(allOrders.get(i));
			}
		}
		
		return undeliveredOrders;
	}
	
	
}
