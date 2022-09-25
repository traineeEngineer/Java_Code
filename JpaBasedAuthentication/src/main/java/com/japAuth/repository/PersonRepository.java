package com.japAuth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.japAuth.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

	Person findByUsername(String username);

}
