package services;

import java.io.IOException;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import beans.Deliverer;
import dao.Deliverers;

public class DelivererService {
	private Deliverers deliverers = new Deliverers();
	
	public Collection<Deliverer> getDeliverers() throws JsonGenerationException, JsonMappingException, IOException {
		return deliverers.load();
	}
}
