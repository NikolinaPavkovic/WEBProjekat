package rest;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.delete;
import static spark.Spark.staticFiles;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;

import beans.Comment;
import beans.Customer;
import beans.Gender;
import beans.Item;
import beans.Restaurant;
import beans.Role;
import beans.User;
import dao.Comments;
import dto.FilterDTO;
import dto.ItemDTO;
import dto.LoginDTO;
import dto.RestaurantDTO;
import dto.SearchDTO;
import dto.UserDTO;
import services.CommentService;
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
	private static CommentService commentService = new CommentService();
	private static Comments comments = new Comments();

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
			System.out.println(name);
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

		put("/rest/editProfile", (req, res) ->{
			res.type("application/json");
			User newUser = g.fromJson(req.body(), User.class);
			Session session = req.session(true);
			User oldUser = session.attribute("user");
			userService.editUser(oldUser, newUser);
			session.invalidate();
			Session sessionNew = req.session(true);
			sessionNew.attribute("user", newUser);
			return "";

		});

		get("/rest/getComments", (req, res) -> {
			res.type("application/json");
			ArrayList<Comment> commentsFromFile = comments.load();
			return g.toJson(commentsFromFile);
		});


		post("/rest/addToCart", (req, res) -> {
			res.type("application/json");
			Item item = g.fromJson(req.body(), Item.class);
			Session session = req.session(true);
			User user = session.attribute("user");
			customerService.addItemToShoppingCart(user, item);
			return "";
		});

		post("/rest/restaurants/findRestaurants", (req, res) -> {
			res.type("application/json");
			System.out.println(req.body());
			System.out.println(restaurantService.findRestaurants(g.fromJson(req.body(), SearchDTO.class)));
			return g.toJson(restaurantService.findRestaurants(g.fromJson(req.body(), SearchDTO.class)));
		});
		
		get("/rest/getCustomer", (req, res) -> {
			res.type("application/json");
			Session session = req.session(true);
			User user = session.attribute("user");
			Customer customer = customerService.getCustomer(user);
			if(customer == null) {
				return "ERROR";
			}
			return g.toJson(customer);
		});
		
		get("/rest/setShoppingCart", (req, res) -> {
			res.type("application/json");
			Session session = req.session(true);
			User user = session.attribute("user");
			customerService.setShoppingCart(user);
			return "";
		});
		
		post("/rest/restaurants/filterRestaurants", (req, res) -> {
			res.type("application/json");
			System.out.println(req.body());
			System.out.println(restaurantService.filterRestaurantsByStatus(g.fromJson(req.body(), FilterDTO.class)));
			return g.toJson(restaurantService.filterRestaurantsByStatus(g.fromJson(req.body(), FilterDTO.class)));
		});


	}
}
