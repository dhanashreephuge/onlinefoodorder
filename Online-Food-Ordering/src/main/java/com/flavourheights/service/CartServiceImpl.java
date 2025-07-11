package com.flavourheights.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flavourheights.model.Cart;
import com.flavourheights.model.CartItem;
import com.flavourheights.model.Food;
import com.flavourheights.model.User;
import com.flavourheights.repository.CartItemRepository;
import com.flavourheights.repository.CartRepository;
import com.flavourheights.repository.FoodRepository;
import com.flavourheights.request.AddCartItemRequest;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private FoodService foodService;
	
	@Override
	public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception {
		User user=userService.findUserByJwtToken(jwt);
		Food food = foodService.findFoodById(req.getFoodId());
		Cart cart = cartRepository.findByCustomerId(user.getId());
		for(CartItem cartItem : cart.getItem()) {
			if(cartItem.getFood().equals(food)) {
				int newQuantity = cartItem.getQuantity()+ req.getQuantity();
				return updateCartItemQuantity(cartItem.getId(),newQuantity);
			}
		}
		
		CartItem newCartItem=new CartItem();
		newCartItem.setFood(food);
		newCartItem.setCart(cart);
		newCartItem.setQuantity(req.getQuantity());
		newCartItem.setIngredients(req.getIngredients());
		newCartItem.setTotalPrice(req.getQuantity()*food.getPrice());
		
		CartItem savedCartItem=cartItemRepository.save(newCartItem);
		cart.getItem().add(savedCartItem);
		
		return savedCartItem;
	}

	@Override
	public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
		Optional<CartItem> cartItemOptional=cartItemRepository.findById(cartItemId);
		if(cartItemOptional.isEmpty()) {
			throw new Exception("cart item not found");
		}
		CartItem item=cartItemOptional.get();
		item.setQuantity(quantity);
		item.setTotalPrice(item.getFood().getPrice()*quantity);
		
		return cartItemRepository.save(item);
	}

	@Override
	public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
		User user = userService.findUserByJwtToken(jwt);
		Cart cart = cartRepository.findByCustomerId(user.getId());
		
		Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
		if(cartItemOptional.isEmpty()) {
			throw new Exception("cart item not found");
		}
		CartItem item = cartItemOptional.get();
		cart.getItem().remove(item);
		return cartRepository.save(cart);
	}

	@Override
	public Long calculateCartTotals(Cart cart) throws Exception {
		Long total = 0L;
		for(CartItem cartItem :cart.getItem()) {
			total+=cartItem.getFood().getPrice()*cartItem.getQuantity();
		}
		return total;
	}

	@Override
	public Cart findCartById(Long id) throws Exception {
		Optional<Cart> optionalCart=cartRepository.findById(id);
		if(optionalCart.isEmpty()) {
			throw new Exception("cart not found with id"+id);
		}
		return optionalCart.get();
	}

	@Override
	public Cart findCartByUserId(Long userId) throws Exception {
		//User user = userService.findUserByJwtToken(jwt);
		Cart cart= cartRepository.findByCustomerId(userId);
		cart.setTotal(calculateCartTotals(cart));
		return cart;
	}

	@Override
	public Cart clearCart(Long userid) throws Exception {
		//User user = userService.findUserByJwtToken(jwt);
		Cart cart = findCartByUserId(userid);
		cart.getItem().clear();
		return cartRepository.save(cart);
	}

}
