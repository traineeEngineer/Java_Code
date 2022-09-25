package com.validateDTO.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.validateDTO.model.Pet;
import com.validateDTO.repositiry.PetRepository;

@Service
public class PetService {
	
	@Autowired
	private PetRepository repo;
	
	public Pet addPet(Pet pet) {
		return repo.save(pet);
	}
	
	public List<Pet> fetchAllPets(){
		return repo.findAll();
	}
	
}
