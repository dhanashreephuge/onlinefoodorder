package com.flavourheights.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flavourheights.model.Cart;

public interface CartRepository extends JpaRepository<Cart,Long>{

	public Cart findByCustomerId(Long userId);
}
