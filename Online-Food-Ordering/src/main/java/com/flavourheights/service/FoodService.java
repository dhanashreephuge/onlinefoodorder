package com.flavourheights.service;

import java.util.*;

import com.flavourheights.model.Category;
import com.flavourheights.model.Food;
import com.flavourheights.model.Restaurant;
import com.flavourheights.request.CreateFoodRequest;

public interface FoodService {

	public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);
	
	void deleteFood(Long foodId) throws Exception;
	
	public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegitarin, boolean isNonVeg, boolean isSeasonal, String foodCategory);
	
	public List<Food> searchFood(String keyword);
	
	public Food findFoodById(Long foodId) throws Exception;
	
	public Food updateAvaibilityStatus(Long foodId) throws Exception;
}
