package com.validateDTO.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.validateDTO.model.Pet;
import com.validateDTO.model.PetDto;
import com.validateDTO.service.PetService;

@RestController
@RequestMapping("/api")
public class PetController {
	
	@Autowired
	private PetService service;
	
	@PostMapping("/add")
	public ResponseEntity<PetDto> savePet(@Valid @RequestBody PetDto petDto){
		Pet pet=new Pet();
		pet.setPetType(petDto.getPetType());
		pet.setPetPrice(petDto.getPetPrice());
		Pet saveRes=  service.addPet(pet);
		
		PetDto pdto=new PetDto();
		pdto.setId(saveRes.getId());
		pdto.setPetType(saveRes.getPetType());
		pdto.setPetPrice(saveRes.getPetPrice());
		
		return new ResponseEntity(pdto,HttpStatus.CREATED);
	}
	
	@GetMapping("/fetch")
	public ResponseEntity<List<PetDto>> revertAll(){
		return new  ResponseEntity(service.fetchAllPets(),HttpStatus.OK);
	}
}
