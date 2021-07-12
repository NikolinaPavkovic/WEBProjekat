package services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import dao.Comments;
import beans.Comment;
import beans.Restaurant;

public class CommentService {
	private Comments comments = new Comments();
	
	public Collection<Comment> getComments() throws JsonGenerationException, JsonMappingException, IOException {
		return this.comments.load();
	}
	
	public double countAverageGrade(String restaurantName) throws JsonGenerationException, JsonMappingException, IOException{
		double avg = 0;
		int cnt = 0;
		int sum = 0;
		for (Comment c : comments.load()) {
			if (c.getRestaurant().getName().equals(restaurantName)) {
				sum += c.getGrade();
				cnt ++;
			}
		}
		
		avg = sum/cnt;
		
		return avg;
	}
	
	public ArrayList<Comment> getRestaurantComments(String restaurantName) throws JsonMappingException, JsonGenerationException, IOException {
		ArrayList<Comment> restaurantComments = new ArrayList<Comment>();
		for (Comment c : comments.load()) {
			if (c.getRestaurant().getName().equals(restaurantName)) {
				restaurantComments.add(c);
			}
		}
		
		return restaurantComments;
	}
	
	public ArrayList<Restaurant> getRestaurantsByGrade(double grade) throws JsonGenerationException, JsonMappingException, IOException{
		ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
		
		for (Comment c : comments.load()) {
			if (grade == countAverageGrade(c.getRestaurant().getName())) {
				restaurants.add(c.getRestaurant());
			}
		}
		
		return restaurants;
	}

}
