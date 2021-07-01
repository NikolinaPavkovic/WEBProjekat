package services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import beans.Customer;
import beans.CustomerType;
import beans.Deliverer;
import beans.Manager;
import beans.Order;
import beans.Restaurant;
import beans.Role;
import beans.ShoppingCart;
import beans.User;
import dao.Customers;
import dao.Deliverers;
import dao.Managers;
import dao.Users;
import dto.LoginDTO;

public class UserService {
	private Users users = new Users();
	private Customers customers = new Customers();
	private Managers managers = new Managers();
	private Deliverers deliverers = new Deliverers();
	
	public Collection<User> getUsers() throws JsonGenerationException, JsonMappingException, IOException {
		return this.users.load();
	}
	
	public User addUser(User user) throws JsonMappingException, JsonGenerationException, IOException {
		User userExists = users.findByUsername(user.getUsername());
		if(userExists != null) {
			return null;
		}
		if(user.getRole() == Role.customer) {
			Customer customer = new Customer(user.getUsername(), user.getPassword(), user.getName(), user.getSurname(), user.getGender(), user.getDateOfBirth(), Role.customer, new ArrayList<Order>(), new ShoppingCart(), 0, new CustomerType());
			customers.save(customer);
		} else if(user.getRole() == Role.manager) {
			Manager manager = new Manager(user.getUsername(), user.getPassword(), user.getName(), user.getSurname(), user.getGender(), user.getDateOfBirth(), Role.manager, new Restaurant());
			managers.save(manager);
		} else if(user.getRole() == Role.deliverer) {
			Deliverer deliverer = new Deliverer(user.getUsername(), user.getPassword(), user.getName(), user.getSurname(), user.getGender(), user.getDateOfBirth(), Role.deliverer, new ArrayList<Order>());
			deliverers.save(deliverer);
		}
		users.save(user);
		return user;
	}
	
	public User login(LoginDTO user) throws JsonGenerationException, JsonMappingException, IOException {
		User userExists = users.findByUsername(user.getUsername());
		if(userExists != null) {
			if(userExists.getPassword().equals(user.getPassword())) {
				return userExists;
			}
		}
		return null;
	}
}
