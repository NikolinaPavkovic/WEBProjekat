package dto;

import java.util.ArrayList;

import beans.Restaurant;

public class FilterDTO {
	private ArrayList<String> type;
	private String status;
	private ArrayList<Restaurant> restaurants;
	boolean ascending;
	
	public FilterDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FilterDTO(ArrayList<String> type, String status, ArrayList<Restaurant> restaurants, boolean ascending) {
		super();
		this.type = type;
		this.status = status;
		this.restaurants = restaurants;
		this.ascending = ascending;
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

	public boolean isAscending() {
		return ascending;
	}

	public void setAscending(boolean ascending) {
		this.ascending = ascending;
	}

}
