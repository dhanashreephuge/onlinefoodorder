package com.flavourheights.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flavourheights.model.OrderItem;

public interface OrderitemRepository extends JpaRepository<OrderItem, Long>{

}
