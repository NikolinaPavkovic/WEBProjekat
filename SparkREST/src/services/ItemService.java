package services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import dao.*;
import beans.Item;
import beans.Restaurant;


public class ItemService {
	private Items items = new Items();
	private Restaurants restaurants = new Restaurants();
	private RestaurantService restaurantService = new RestaurantService();
	
	public Collection<Item> getItems() throws JsonGenerationException, JsonMappingException, IOException {
		return this.items.load();
	}
	
	public Item addItem(Item item) throws JsonMappingException, JsonGenerationException, IOException {
		items.save(item);
		
	/*	for (Restaurant restaurant : restaurants.load()) {
			if (item.getRestaurant().getName().equals(restaurant.getName())) {
				restaurantService.addItemToRestaurant(restaurant, item);
				restaurants.save(restaurant);
			}
		} */
		
		return item;
	}
	
	public ArrayList<Item> getItemsForRestaurant(String restaurantName) throws JsonGenerationException, JsonMappingException, IOException {
		ArrayList<Item> restaurantItems = new ArrayList<Item>();
		
		for (Item item : items.load()) {
			if (item.getRestaurant().getName().equals(restaurantName)) {
				restaurantItems.add(item);
			}
		}
		
		return restaurantItems;
	}
}
