package com.flavourheights.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flavourheights.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	public List<Category> findByRestaurantId(Long id);
}
