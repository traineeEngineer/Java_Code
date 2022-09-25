package com.Users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Users.VO.ResponseTemplateVO;
import com.Users.model.User;
import com.Users.service.UserServices;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

	@Autowired
	private UserServices service;
	
	
	@PostMapping("/")
	public User  saveUser(@RequestBody User user) {
		log.info("inside save user of user controller");
		return service.saveUser(user);
	}
	
	@GetMapping("/{id}")
	public ResponseTemplateVO getUserWithDepartment(@PathVariable(name="id") Long userId) {
		log.info("inside  getUserWithDepartment userController");
		 return service.getUserWithDepartment(userId);
	}
	
}
