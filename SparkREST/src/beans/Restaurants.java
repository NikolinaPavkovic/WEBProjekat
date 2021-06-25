package beans;

import java.util.ArrayList;
import java.util.HashMap;

public class Restaurants {
	private HashMap<String, Restaurant> restaurants = new HashMap<String, Restaurant>();
	private ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
	
	public Restaurants() {
		Restaurant rest = new Restaurant("Petrus", RestaurantType.grill, new ArrayList<Item>(), RestaurantStatus.open, new Location());
		restaurants.put(rest.getName(), rest);
	}

	public HashMap<String, Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(HashMap<String, Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

	public ArrayList<Restaurant> getRestaurantList() {
		Restaurant rest = new Restaurant("Petrus", RestaurantType.grill, new ArrayList<Item>(), RestaurantStatus.open, new Location());
		restaurantList.add(rest);
		return restaurantList;
	}

	public void setRestaurantList(ArrayList<Restaurant> restaurantList) {
		this.restaurantList = restaurantList;
	}
	
	
}
