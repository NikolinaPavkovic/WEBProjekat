package services;

import java.util.Collection;

import beans.Restaurant;
import dao.Restaurants;

public class RestaurantService {
	private Restaurants restaurants = new Restaurants();
	
	public Collection<Restaurant> getRestaurants(){
		return this.restaurants.getRestaurantList();
	}
	
	public void addRestaurant() {
		
	}
}
