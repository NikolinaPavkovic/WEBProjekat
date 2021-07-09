package dto;

public class SearchDTO {
	private String restaurantName;
	private String city;
	private String location;
	private String country;
	private double grade;
	private String restaurantType;
	
	public SearchDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SearchDTO(String restaurantName, String city, String location, String country, double grade,
			String restaurantType) {
		super();
		this.restaurantName = restaurantName;
		this.city = city;
		this.location = location;
		this.country = country;
		this.grade = grade;
		this.restaurantType = restaurantType;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public String getRestaurantType() {
		return restaurantType;
	}

	public void setRestaurantType(String restaurantType) {
		this.restaurantType = restaurantType;
	}

}
