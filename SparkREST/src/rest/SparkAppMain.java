package rest;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import java.io.File;

import com.google.gson.Gson;

import beans.Role;
import beans.User;
import services.RestaurantService;
import services.UserService;

public class SparkAppMain {

	private static Gson g = new Gson();
	private static RestaurantService restaurantService = new RestaurantService();
	private static UserService userService = new UserService();
	
	public static void main(String[] args) throws Exception {
		port(8080);

		staticFiles.externalLocation(new File("./static").getCanonicalPath());
		
		get("rest/restaurants/", (req, res) -> {
			res.type("application/json");
			return g.toJson(restaurantService.getRestaurants());
		});
		
		post("rest/register/", (req, res) -> {
			res.type("application/json");
			User user = g.fromJson(req.body(), User.class);
			user.setRole(Role.customer);
			userService.addUser(user);
			return "SUCCESS";
		});
		
	}
}
