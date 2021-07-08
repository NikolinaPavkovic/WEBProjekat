package services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import beans.Deliverer;
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
				oldDeliverer.getOrders());
		delivererList.add(newDeliverer);
		deliverers.emptyFile();
		for (Deliverer deliverer : delivererList) {
			deliverers.save(deliverer);
		}
	}
}
