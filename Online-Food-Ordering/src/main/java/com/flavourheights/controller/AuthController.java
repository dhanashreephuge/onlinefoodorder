package com.flavourheights.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


import com.flavourheights.config.JwtProvider;
import com.flavourheights.model.Cart;
import com.flavourheights.model.USER_ROLE;
import com.flavourheights.model.User;
import com.flavourheights.repository.CartRepository;
import com.flavourheights.repository.UserRepository;
import com.flavourheights.request.LoginRequest;
import com.flavourheights.response.AuthResponse;
import com.flavourheights.service.CustomerUserDetailsService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private CustomerUserDetailsService  customerUserDetailsService;
	
	@Autowired
	private CartRepository cartRepository;
	
	 @PostMapping("/signup")
	public ResponseEntity<AuthResponse>createUserHandler(@RequestBody User user){
		
		User isEmailExist=userRepository.findByEmail(user.getEmail());
		if(isEmailExist!= null) {
			throw new RuntimeException("Email is already used with another account");
		}
		
		User createdUser=new User();
		createdUser.setEmail(user.getEmail());
		createdUser.setFullname(user.getFullname());
		createdUser.setRole(user.getRole());
		createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
		
		User savedUser = userRepository.save(createdUser);
		
		Cart cart=new Cart();
		cart.setCustomer(savedUser);
		cartRepository.save(cart);
		
		Authentication authentication=new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt=jwtProvider.generateToken(authentication);
		
		AuthResponse authResponse=new AuthResponse();
		authResponse.setJwt(jwt);
		authResponse.setMessage("Register success");
		authResponse.setRole(savedUser.getRole());
		
		return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
	}
	 
	 @PostMapping("/signin")
	 public ResponseEntity<AuthResponse> singnin(@RequestBody LoginRequest req){
		 
		 String username = req.getEmail();
		 String password = req.getPassword();
		 
		 Authentication authentication = authenticate(username,password);
		 
		 Collection<? extends GrantedAuthority>authorities=authentication.getAuthorities();
		 String role=authorities.isEmpty()?null:authorities.iterator().next().getAuthority();
		 
		 String jwt=jwtProvider.generateToken(authentication);
			
			AuthResponse authResponse=new AuthResponse();
			authResponse.setJwt(jwt);
			authResponse.setMessage("Login success");
			authResponse.setRole(USER_ROLE.valueOf(role));
		 
		 return new ResponseEntity<>(authResponse, HttpStatus.OK);
	 }

	private Authentication authenticate(String username, String password) {
		
		UserDetails userDetails =customerUserDetailsService.loadUserByUsername(username);
		
		if(userDetails==null) {
			throw new BadCredentialsException("Invalid username...");
		}
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid password...");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}
}
