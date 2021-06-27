package services;

import java.io.IOException;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import beans.User;
import dao.Users;

public class UserService {
	private Users users = new Users();
	
	public Collection<User> getUsers() throws JsonGenerationException, JsonMappingException, IOException {
		return this.users.load();
	}
	
	public void addUser(User user) throws JsonMappingException, JsonGenerationException, IOException {
		this.users.save(user);
	}
}
