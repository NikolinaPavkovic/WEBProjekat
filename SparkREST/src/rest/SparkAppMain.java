package rest;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;

import beans.Gender;
import beans.Item;
import beans.Restaurant;
import beans.Role;
import beans.User;
import dto.ItemDTO;
import dto.LoginDTO;
import dto.RestaurantDTO;
import dto.UserDTO;
import services.CustomerService;
import services.DelivererService;
import services.ItemService;
import services.ManagerService;
import services.RestaurantService;
import services.UserService;
import spark.Session;

public class SparkAppMain {

	private static Gson g = new Gson();
	private static RestaurantService restaurantService = new RestaurantService();
	private static UserService userService = new UserService();
	private static CustomerService customerService = new CustomerService();
	private static ManagerService managerService = new ManagerService();
	private static DelivererService delivererService = new DelivererService();
	private static ItemService itemService = new ItemService();

	public static void main(String[] args) throws Exception {
		port(8080);

		staticFiles.externalLocation(new File("./static").getCanonicalPath());

		get("rest/restaurants/", (req, res) -> {
			res.type("application/json");
			return g.toJson(restaurantService.getRestaurants());
		});

		post("/rest/register", (req, res) -> {
			res.type("application/json");
			UserDTO userDTO = g.fromJson(req.body(), UserDTO.class);
			Gender gender;
			Date date;
			if(userDTO.getGender().equals("male")) {
				gender = Gender.male;
			} else {
				gender = Gender.female;
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			date = (Date) format.parse(userDTO.getDateOfBirth());
			User user = new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getName(), userDTO.getSurname(), gender, date, Role.customer, false);
			userService.addUser(user);
			Session session = req.session(true);
			User isLoggedIn = session.attribute("user");
			if(isLoggedIn == null) {
				session.attribute("user", user);
			}
			return "SUCCESS";
		});


		post("/rest/addEmployee", (req, res) -> {
			res.type("application/json");
			UserDTO userDTO = g.fromJson(req.body(), UserDTO.class);
			Gender gender;
			Date date;
			Role role;
			if(userDTO.getGender().equals("male")) {
				gender = Gender.male;
			} else {
				gender = Gender.female;
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			date = (Date) format.parse(userDTO.getDateOfBirth());
			if(userDTO.getRole().equals("manager")) {
				role = Role.manager;
			} else {
				role = Role.deliverer;
			}
			User user = new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getName(), userDTO.getSurname(), gender, date, role, false);
			userService.addUser(user);
			return "SUCCESS";
		});



		post("/rest/addRestaurant", (req, res) -> {
			try {
				res.type("application/json");
				RestaurantDTO restaurantDTO = g.fromJson(req.body(), RestaurantDTO.class);
				Restaurant restaurant = new Restaurant(restaurantDTO.getName(), restaurantDTO.getType(), restaurantDTO.getItems(), restaurantDTO.getStatus(),
						restaurantDTO.getLocation(), restaurantDTO.getImgPath());
				restaurantService.addRestaurant(restaurant);
				return "SUCCESS";

			} catch (Exception e) {
				e.printStackTrace();
				return "";
			}
		});

		post("/rest/login", (req, res) -> {
			res.type("application/json");
			LoginDTO loginDTO = g.fromJson(req.body(), LoginDTO.class);
			User user = userService.login(loginDTO);
			if(user != null) {
				Session session = req.session(true);
				User isLoggedIn = session.attribute("user");
				if(isLoggedIn == null) {
					session.attribute("user", user);
				}
			} else {
				return "";
			}
			return g.toJson(user);
		});

		get("/rest/isLogged", (req, res) -> {
			res.type("application/json");
			Session session = req.session(true);
			User user = session.attribute("user");
			return g.toJson(user);
		});

		get("/rest/logout", (req, res) -> {
			res.type("application/json");
			Session session = req.session(true);
			if (session.attribute("user") != null) {
				session.invalidate();
			}
			return "SUCCESS";
		});

		get("/rest/restaurants/:name", (req, res) -> {
			res.type("application/json");
			String name = req.params("name");
			Restaurant restaurant = restaurantService.getRestaurantByName(name);
			restaurant.setItems(itemService.getItemsForRestaurant(name));
			return g.toJson(restaurant);
		});

		get("/rest/customers", (req, res) -> {
			res.type("application/json");
			return g.toJson(customerService.getCustomers());
		});

		get("/rest/managers", (req, res) -> {
			res.type("application/json");
			return g.toJson(managerService.getManagers());
		});

		get("/rest/deliverers", (req, res) -> {
			res.type("application/json");
			return g.toJson(delivererService.getDeliverers());
		});


		post ("/rest/addItem", (req, res) -> {
			try {
				res.type("application/json");
				ItemDTO itemDTO = g.fromJson(req.body(), ItemDTO.class);
				Item item = new Item(itemDTO.getName(), itemDTO.getPrice(), itemDTO.getType(), restaurantService.getRestaurantByName(itemDTO.getRestaurant().getName()),
						itemDTO.getAmount(), itemDTO.getDescription(), itemDTO.getImagePath());
				itemService.addItem(item);
				return "SUCCESS";
			} catch (Exception e) {
				 e.printStackTrace();
				 return "";
			}
		});

		post("/rest/editProfile", (req, res) ->{
			res.type("application/json");
			User user = g.fromJson(req.body(), User.class);
			return "";

		});

	}
}
