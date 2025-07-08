package com.flavourheights.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flavourheights.model.Category;
import com.flavourheights.model.Food;
import com.flavourheights.model.Restaurant;
import com.flavourheights.repository.FoodRepository;
import com.flavourheights.request.CreateFoodRequest;

@Service
public class FoodServiceImp implements FoodService{
	
	@Autowired
	private FoodRepository foodRepository;

	@Override
	public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) {
		Food food = new Food();
		food.setFoodCategory(category);
		food.setRestaurant(restaurant);
		food.setDescription(req.getDescription());
		food.setImages(req.getImages());
		food.setName(req.getName());
		food.setPrice(req.getPrice());
		food.setIngredients(req.getIngredients());
		food.setSeasonal(req.isSeasonal());
		//food.setVegetarian(req.isVegetarin());
		food.setCreationDate(new Date());
		food.setVegetarian(req.isVegetarin());
		
		Food savedFood=foodRepository.save(food);
		restaurant.getFoods().add(savedFood);
		
		return savedFood;
		
	}

	@Override
	public void deleteFood(Long foodId) throws Exception {
		
		Food food = findFoodById(foodId);
		food.setRestaurant(null);
		foodRepository.save(food);
	}

	@Override
	public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegitarin, boolean isNonVeg, boolean isSeasonal,
			String foodCategory) {
		
		List<Food> foods = foodRepository.findByRestaurantId(restaurantId);
		
		if(isVegitarin) {
			foods=filterByVegitarin(foods,isVegitarin);
		}
		if(isNonVeg) {
			foods=filterByNonVeg(foods,isNonVeg);
		}
		if(isSeasonal) {
			foods=filterBySeasonal(foods,isSeasonal);
		}
		if(foodCategory!=null && !foodCategory.equals("")) {
			foods=filterByCategory(foods,foodCategory);
		}
		
		return foods;
	}

	private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
		// TODO Auto-generated method stub
		return foods.stream().filter(food ->{
			if(food.getFoodCategory()!=null) {
				return food.getFoodCategory().getName().equals(foodCategory);
			}
			return false;
		}).collect(Collectors.toList());
	}

	private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
		// TODO Auto-generated method stub
		return foods.stream().filter(food -> food.isSeasonal()==isSeasonal).collect(Collectors.toList());
	}

	private List<Food> filterByNonVeg(List<Food> foods, boolean isNonVeg) {
		// TODO Auto-generated method stub
		return foods.stream().filter(food -> food.isVegetarian()==false).collect(Collectors.toList());
	}

	private List<Food> filterByVegitarin(List<Food> foods, boolean isVegitarin) {
		return foods.stream().filter(food -> food.isVegetarian()==isVegitarin).collect(Collectors.toList());
	}

	@Override
	public List<Food> searchFood(String keyword) {
		
		return foodRepository.searchFood(keyword);
	}

	@Override
	public Food findFoodById(Long foodId) throws Exception {
		Optional<Food> optionalFood = foodRepository.findById(foodId);
		
		if(optionalFood.isEmpty()) {
			throw new Exception("food not exist...");
		}
		
		return optionalFood.get();
	}

	@Override
	public Food updateAvaibilityStatus(Long foodId) throws Exception {
		Food food=findFoodById(foodId);
		food.setAvailable(!food.isAvailable());
		
		return foodRepository.save(food);
	}

}
