package com.flavourheights.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flavourheights.model.Category;
import com.flavourheights.model.Restaurant;
import com.flavourheights.repository.CategoryRepository;

@Service
public class CategoryServiceImp implements CategoryService{
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category createCategory(String name, Long userId) throws Exception {
		Restaurant restaurant=restaurantService.getRestaurantByUserId(userId);
		Category category=new Category();
		category.setName(name);
		category.setRestaurant(restaurant);
		
		return categoryRepository.save(category);
	}

	@Override
	public List<Category> findCategoryByRestaurantId(Long id) throws Exception {
		Restaurant restaurant=restaurantService.getRestaurantByUserId(id);
				return categoryRepository.findByRestaurantId(id);
	}

	@Override
	public Category findCategoryById(Long id) throws Exception {
		Optional<Category> optionalCategory=categoryRepository.findById(id);
		
		if(optionalCategory.isEmpty()) {
			throw new Exception("category not found");
		}
		return optionalCategory.get();
	}

}
