package com.validateDTO.repositiry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.validateDTO.model.Pet;


@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {

}
