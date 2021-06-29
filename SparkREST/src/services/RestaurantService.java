package services;

import java.io.IOException;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import beans.Restaurant;
import dao.Restaurants;

public class RestaurantService {
	private Restaurants restaurants = new Restaurants();
	
	public Collection<Restaurant> getRestaurants() throws JsonGenerationException, JsonMappingException, IOException{
		return this.restaurants.load();
	}
	
	public Restaurant addRestaurant(Restaurant restaurant) throws JsonGenerationException, JsonMappingException, IOException {
		restaurants.save(restaurant);
		return restaurant;
	}
}
