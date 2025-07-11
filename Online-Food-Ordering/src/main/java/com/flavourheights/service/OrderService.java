package com.flavourheights.service;

import java.util.List;

import com.flavourheights.model.Order;
import com.flavourheights.model.User;
import com.flavourheights.request.OrderRequest;

public interface OrderService {

	public Order createOrder(OrderRequest order, User user) throws Exception;
	
	public Order updateOrder(Long orderId, String orderStatus) throws Exception;
	
	public void cancelOrder(Long orderId) throws Exception;
	
	public List<Order> getUsersOrder(Long userId) throws Exception;
	
	public List<Order> getRestaurantsOrder(Long restaurantId,String orderStatus) throws Exception;
	
	public Order findOrderById(Long orderId)throws Exception;
}
