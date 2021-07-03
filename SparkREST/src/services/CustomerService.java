package services;

import java.io.IOException;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import beans.Customer;
import dao.Customers;

public class CustomerService {
	private Customers customers = new Customers();
	
	public Collection<Customer> getCustomers() throws JsonGenerationException, JsonMappingException, IOException {
		return this.customers.load();
	}
}
