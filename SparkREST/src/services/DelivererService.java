package services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import beans.Deliverer;
import beans.Order;
import beans.User;
import dao.Deliverers;

public class DelivererService {
	private Deliverers deliverers = new Deliverers();
	
	public Collection<Deliverer> getDeliverers() throws JsonGenerationException, JsonMappingException, IOException {
		return deliverers.load();
	}
	
	public void editProfile(User oldUser, User newUser) throws JsonGenerationException, JsonMappingException, IOException {
		ArrayList<Deliverer> delivererList = deliverers.load();
		Deliverer oldDeliverer = new Deliverer();
		for (int i = 0; i < delivererList.size(); i++) {
			if(delivererList.get(i).getUsername().equals(oldUser.getUsername())) {
				delivererList.remove(i);
				oldDeliverer = new Deliverer(delivererList.get(i).getUsername(),
						delivererList.get(i).getPassword(),
						delivererList.get(i).getName(),
						delivererList.get(i).getSurname(),
						delivererList.get(i).getGender(),
						delivererList.get(i).getDateOfBirth(),
						delivererList.get(i).getRole(),
						delivererList.get(i).getIsBlocked(),
						delivererList.get(i).isDeleted(),
						delivererList.get(i).getOrders());
				break;
			}
		}
		Deliverer newDeliverer = new Deliverer(newUser.getUsername(),
				newUser.getPassword(),
				newUser.getName(),
				newUser.getSurname(),
				newUser.getGender(),
				newUser.getDateOfBirth(),
				newUser.getRole(),
				newUser.getIsBlocked(),
				newUser.isDeleted(),
				oldDeliverer.getOrders());
		delivererList.add(newDeliverer);
		deliverers.emptyFile();
		for (Deliverer deliverer : delivererList) {
			deliverers.save(deliverer);
		}
	}
	
	public void deleteDeliverer(String username) throws JsonGenerationException, JsonMappingException, IOException {
		ArrayList<Deliverer> delivererList = deliverers.load();
		Deliverer deliverer = new Deliverer();
		for(int i = 0; i < delivererList.size(); i++) {
			if(delivererList.get(i).getUsername().equals(username)) {
				deliverer = delivererList.get(i);
				delivererList.remove(i);
			}
		}
		deliverer.setDeleted(true);
		delivererList.add(deliverer);
		deliverers.emptyFile();
		for (Deliverer d : delivererList) {
			deliverers.save(d);
		}
	}
	
	public ArrayList<Deliverer> getActiveDeliverers() throws JsonGenerationException, JsonMappingException, IOException {
		 ArrayList<Deliverer> activeDeliverers = new ArrayList<Deliverer>();
		 ArrayList<Deliverer> allDeliverers = deliverers.load();
		 for (int i = 0; i < allDeliverers.size(); i++) {
			if(allDeliverers.get(i).isDeleted() == false) {
				activeDeliverers.add(allDeliverers.get(i));
			}
		}
		 return activeDeliverers;
	 }
	
	public ArrayList<Order> getDelivererOrders(User user) throws JsonGenerationException, JsonMappingException, IOException {
		ArrayList<Deliverer> allDeliverers =  deliverers.load();
		Deliverer deliverer = new Deliverer();
		for(int i = 0; i < allDeliverers.size(); i++) {
			if(allDeliverers.get(i).getUsername().equals(user.getUsername())) {
				deliverer = allDeliverers.get(i);
			}
		}
		return deliverer.getOrders();
	}
}
