package com.flavourheights.request;

import java.util.*;

import com.flavourheights.model.Category;
import com.flavourheights.model.IngredientsItem;

public class CreateFoodRequest {

	private String name;
	private String description;
	private Long price;
	private Category category;
	private List<String> images;
	private Long restaurantId;
	private boolean vegetarin;
	private List<IngredientsItem> ingredients;
	private boolean isSeasonal;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public List<String> getImages() {
		return images;
	}
	public void setImages(List<String> images) {
		this.images = images;
	}
	public Long getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}
	public boolean isVegetarin() {
		return vegetarin;
	}
	public void setVegetarin(boolean vegetarin) {
		this.vegetarin = vegetarin;
	}
	public List<IngredientsItem> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<IngredientsItem> ingredients) {
		this.ingredients = ingredients;
	}
	public boolean isSeasonal() {
		return isSeasonal;
	}
	public void setSeasonal(boolean isSeasonal) {
		this.isSeasonal = isSeasonal;
	}
	
	
}
