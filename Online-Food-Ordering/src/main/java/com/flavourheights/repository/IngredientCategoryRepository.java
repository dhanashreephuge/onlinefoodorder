package com.flavourheights.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flavourheights.model.IngredientCategory;

public interface IngredientCategoryRepository extends JpaRepository<IngredientCategory, Long>{

	List<IngredientCategory> findByRestaurantId(Long id);
}
