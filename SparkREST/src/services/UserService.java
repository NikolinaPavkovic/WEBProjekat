package services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import beans.Customer;
import beans.CustomerType;
import beans.Order;
import beans.ShoppingCart;
import beans.TypeName;
import beans.User;
import dao.Customers;
import dao.Orders;
import dao.Users;

public class UserService {
	private Users users = new Users();
	private Customers customers = new Customers();
	
	public Collection<User> getUsers() throws JsonGenerationException, JsonMappingException, IOException {
		return this.users.load();
	}
	
	public User addUser(User user) throws JsonMappingException, JsonGenerationException, IOException {
		User userExists = users.findByUsername(user.getUsername());
		if(userExists != null) {
			return null;
		}
		users.save(user);
		return user;
	}
}
