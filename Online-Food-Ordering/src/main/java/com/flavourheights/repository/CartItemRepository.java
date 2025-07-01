package com.flavourheights.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flavourheights.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{

}
