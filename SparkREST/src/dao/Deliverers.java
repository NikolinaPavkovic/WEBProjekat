package dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.*;

public class Deliverers {
	private HashMap<String, Deliverer> deliverers = new HashMap<String, Deliverer>();
	
	public Deliverers() {
		
	}

	public HashMap<String, Deliverer> getDeliverers() {
		return deliverers;
	}

	public void setDeliverers(HashMap<String, Deliverer> deliverers) {
		this.deliverers = deliverers;
	}
	
	public void save(Deliverer deliverer) throws JsonMappingException, JsonGenerationException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<Deliverer> deliverers = load();
		deliverers.add(deliverer);
		mapper.writeValue(new File("deliverers.json"), deliverers);
	}
	
	public ArrayList<Deliverer> load() throws JsonGenerationException, JsonMappingException, IOException {
		ArrayList<Deliverer> deliverersFromFile = new ArrayList<Deliverer>();
		final ObjectMapper mapper = new ObjectMapper();
		ArrayList<Deliverer> deliverers = mapper.readValue(new File("deliverers.json"), new TypeReference<List<Deliverer>>(){});
		deliverers.forEach(d -> deliverersFromFile.add(d));
		
		return deliverersFromFile;
	}

}
