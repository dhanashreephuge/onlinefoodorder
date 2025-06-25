package com.flavourheights.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flavourheights.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

	public static void main(String[] args) {
		
	}

}
