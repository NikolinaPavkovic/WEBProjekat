package dto;

import java.util.ArrayList;

import beans.Restaurant;

public class FilterDTO {
	private ArrayList<String> type;
	private String status;
	private ArrayList<Restaurant> restaurants;
	
	public FilterDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FilterDTO(ArrayList<String> type, String status, ArrayList<Restaurant> restaurants) {
		super();
		this.type = type;
		this.status = status;
		this.restaurants = restaurants;
	}

	public ArrayList<String> getType() {
		return type;
	}

	public void setType(ArrayList<String> type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ArrayList<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(ArrayList<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

	
}
