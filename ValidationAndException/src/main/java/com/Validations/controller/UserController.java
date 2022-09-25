package com.Validations.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Validations.exceptions.UserNotFoundException;
import com.Validations.model.User;
import com.Validations.model.UserDto;
import com.Validations.service.Userservices;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private Userservices service;
	
	
	@PostMapping("/signup")
	public ResponseEntity<User> saveUser( @RequestBody @Valid UserDto dto){
		return new ResponseEntity<User>(service.saveUser(dto),HttpStatus.CREATED);
	}
	
	@GetMapping("/fetchAll")
	public ResponseEntity<List<User>> getAllUsers(){
		return ResponseEntity.ok(service.getAllUsers());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUSer(@PathVariable Integer id) throws UserNotFoundException{
		return ResponseEntity.ok(service.getUser(id));
	}
}
