package com.mars.assignment.usermanagement.repository;

import com.mars.assignment.usermanagement.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
