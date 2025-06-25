package com.flavourheights.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flavourheights.model.Food;

public interface FoodRepository extends JpaRepository<Food, Long>{

	List<Food> findByRestaurantId(Long restaurantId);
	
	@Query("SELECT f FROM Food f WHERE f.name LIKE %:keyword% OR f.foodCategory.name LIKE %:keyword%")
	List<Food> searchFood(@Param("keyword") String keyword);
}
