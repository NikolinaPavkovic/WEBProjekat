package beans;

import java.util.ArrayList;


public class Restaurant {
	private String name;
	private RestaurantType type;
	private ArrayList<Item> items;
	private RestaurantStatus status;
	private Location location;
	private String imgPath;
	private double averageGrade;
	private boolean deleted;
	
	public Restaurant() {
		super();
	}
	
    public Restaurant(String name, RestaurantType type, ArrayList<Item> items, RestaurantStatus status,
			Location location, String imgPath, boolean deleted) {
		super();
		this.name = name;
		this.type = type;
		this.items = items;
		this.status = status;
		this.location = location;
		this.imgPath = imgPath;
		this.deleted = deleted;
	} 

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RestaurantType getType() {
		return type;
	}

	public void setType(RestaurantType type) {
		this.type = type;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	public RestaurantStatus getStatus() {
		return status;
	}

	public void setStatus(RestaurantStatus status) {
		this.status = status;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	} 

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public double getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(double averageGrade) {
		this.averageGrade = averageGrade;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
}
