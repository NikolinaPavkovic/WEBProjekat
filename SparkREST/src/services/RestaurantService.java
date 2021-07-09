package services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import beans.Item;
import beans.Restaurant;
import dao.Restaurants;
import dto.SearchDTO;

public class RestaurantService {
	private Restaurants restaurants = new Restaurants();
	private static CommentService commentService = new CommentService();
	private static ItemService itemService = new ItemService();
	
	public Collection<Restaurant> getRestaurants() throws JsonGenerationException, JsonMappingException, IOException{
		return this.restaurants.load();
	}
	
	public Restaurant addRestaurant(Restaurant restaurant) throws JsonGenerationException, JsonMappingException, IOException {
		restaurants.save(restaurant);
		return restaurant;
	}
	
	public Restaurant getRestaurantByName(String name) {
		Restaurant restaurant = new Restaurant();
		
		try {
			for (Restaurant r : restaurants.load()) {
				if (r.getName().equals(name)) {
					restaurant = r;
				}
			}
			return restaurant;
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch(JsonGenerationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return restaurant;
	}
	
	public ArrayList<Restaurant> findRestaurants(SearchDTO fromJson) throws IOException {
		ArrayList<Restaurant> filteredRestaurants = filterRestaurants(fromJson);
		ArrayList<Restaurant> found = new ArrayList<Restaurant>();
		
		
		return found;
	}
	
	private boolean doesNameExists(String jsonName, String restaurantName) {
		if (!jsonName.isEmpty()) {
			if (jsonName.equals(restaurantName)) {
				return true;
			} else {
				return false;
			}
		}
		
		return false;
	}
	
	public ArrayList<Restaurant> filterRestaurants(SearchDTO fromJson) throws JsonGenerationException, JsonMappingException, IOException {
		ArrayList<Restaurant> filteredRestaurants = new ArrayList<Restaurant>();
		ArrayList<Restaurant> allRestaurants = this.restaurants.load();
		boolean canAdd = false;
		
		for (Restaurant r : allRestaurants) {
			if (fromJson.getCity().isEmpty()) {
				if (!fromJson.getCountry().isEmpty()) {
					if (fromJson.getCountry().toLowerCase().equals(r.getLocation().getAddress().getCity().getState().getState().toLowerCase())) {
						canAdd = true;
					} else {
						continue;
						//canAdd = false;
					}
				}
			} else if (!fromJson.getCity().isEmpty()) {
				if (!fromJson.getCountry().isEmpty()) {
					if (fromJson.getCountry().toLowerCase().equals(r.getLocation().getAddress().getCity().getState().getState().toLowerCase()) 
							&& fromJson.getCity().toLowerCase().equals(r.getLocation().getAddress().getCity().getCity().toLowerCase())) {
						canAdd = true;
					} else {
						continue;
						//canAdd = false;
					}
				}
			}
			
			if (!fromJson.getRestaurantName().isEmpty()) {
				if (fromJson.getRestaurantName().equals(r.getName())) {
					canAdd = true;
				} else {
					//canAdd = false;
					continue;
				}
			} 
			
			if (!fromJson.getRestaurantType().isEmpty()) {
				if (fromJson.getRestaurantType().equals(r.getType().toString())) {
					canAdd = true;
				} else {
					//canAdd = false;
					continue;
				}
			} 
			
			if (fromJson.getGrade() != 0) {
				if (fromJson.getGrade() == commentService.countAverageGrade(fromJson.getRestaurantName())) {
					canAdd = true;
				} else {
					//canAdd = false;
					continue;
				}
			} 
			
			if (canAdd == true) {
				ArrayList<Item> items = new ArrayList<Item>();
				for (Item i : itemService.getItemsForRestaurant(r.getName())) {
					items.add(i);
				}
				r.setItems(items);
				filteredRestaurants.add(r);
			}
		}
		return filteredRestaurants;
	}
	
}
