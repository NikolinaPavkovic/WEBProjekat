package services;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import beans.Customer;
import beans.Item;
import beans.Role;
import beans.ShoppingCart;
import beans.User;
import dao.Customers;
import dao.Users;

public class CustomerService {
	private Customers customers = new Customers();
	private Users users = new Users();
	
	public Collection<Customer> getCustomers() throws JsonGenerationException, JsonMappingException, IOException {
		return this.customers.load();
	}
	
	public void addProductToCart(User user, Item item) throws JsonGenerationException, JsonMappingException, IOException {
		ArrayList<Customer> customerList = customers.load();
		for(int i = 0; i < customerList.size(); i++) {
			if(user.getUsername().equals(customerList.get(i).getUsername())) {
				customerList.get(i).getShoppingCart().getItems().add(item);
			}
		}
	}
	
	/*public Customer editCustomer(Customer oldCustomer, Customer newCustomer) throws JsonGenerationException, JsonMappingException, IOException {
		ArrayList<Customer> customerList = customers.load();
		for (int i = 0; i < customerList.size(); i++) {
			if(customerList.get(i).getUsername().equals(oldCustomer.getUsername())) {
				customerList.remove(i);
			}
		}
		customerList.add(newCustomer);
		customers.emptyFile();
		for(Customer customer : customerList) {
			customers.save(customer);
		}
		return null;
	}*/
	
	
	 public Customer editProfile(User oldUser, User newUser) throws JsonGenerationException, JsonMappingException, IOException {
		ArrayList<Customer> customerList = customers.load();
		Customer oldCustomer = new Customer();
		for (int i = 0; i < customerList.size(); i++) {
			if(customerList.get(i).getUsername().equals(oldUser.getUsername())) {
				customerList.remove(i);
				oldCustomer = new Customer(customerList.get(i).getUsername(),
						customerList.get(i).getPassword(),
						customerList.get(i).getName(),
						customerList.get(i).getSurname(),
						customerList.get(i).getGender(),
						customerList.get(i).getDateOfBirth(),
						customerList.get(i).getRole(),
						customerList.get(i).getIsBlocked(),
						customerList.get(i).getOrders(),
						customerList.get(i).getShoppingCart(),
						customerList.get(i).getPoints(),
						customerList.get(i).getCustomerType());
				break;
			}
		}
		Customer newCustomer = new Customer(newUser.getUsername(),
				newUser.getPassword(),
				newUser.getName(),
				newUser.getSurname(),
				newUser.getGender(),
				newUser.getDateOfBirth(),
				newUser.getRole(),
				newUser.getIsBlocked(),
				oldCustomer.getOrders(),
				oldCustomer.getShoppingCart(),
				oldCustomer.getPoints(),
				oldCustomer.getCustomerType());
		customerList.add(newCustomer);
		customers.emptyFile();
		for(Customer customer : customerList) {
			customers.save(customer);
		}
		return null;
	}
	 
	 public void addItemToShoppingCart(User user, Item item) throws JsonGenerationException, JsonMappingException, IOException {
		 ArrayList<Customer> customerList = customers.load();
		 Customer newCustomer = new Customer();
		 for (int i = 0; i < customerList.size(); i++) {
			if(user.getUsername().equals(customerList.get(i).getUsername())) {
				newCustomer = customerList.get(i);
				if(newCustomer.getShoppingCart().getItems() == null) {
					newCustomer.getShoppingCart().setItems(new ArrayList<Item>());
				}
				newCustomer.getShoppingCart().getItems().add(item);
				customerList.remove(i);
				customerList.add(newCustomer);
			}
		 }
		 customers.emptyFile();
		 for (Customer customer : customerList) {
			customers.save(customer);
		}
	 }
	 
	 public void setShoppingCart(User user) throws JsonGenerationException, JsonMappingException, IOException {
		 ArrayList<Customer> customerList = customers.load();
		 Customer newCustomer = new Customer();
		 for (int i = 0; i < customerList.size(); i++) {
			 
		 }
	 }

}
