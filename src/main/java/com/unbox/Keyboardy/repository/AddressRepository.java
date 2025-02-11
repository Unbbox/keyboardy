package com.unbox.Keyboardy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unbox.Keyboardy.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    
}
