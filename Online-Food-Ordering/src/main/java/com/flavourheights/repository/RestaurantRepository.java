package com.flavourheights.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.flavourheights.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{
	
	@Query("SELECT r FROM Restaurant r WHERE lower(r.name) LIKE lower(concat('%', :query, '%'))"+
	" OR lower(r.cuisineType) LIKE lower(concat('%', :query,'%'))")
	List<Restaurant> findBySearchQuery(String query);

	Restaurant findByOwnerId(Long userId);
}
