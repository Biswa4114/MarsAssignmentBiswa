package com.mars.assignment.usermanagement.repository;

import com.mars.assignment.usermanagement.entity.Address;
import com.mars.assignment.usermanagement.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
