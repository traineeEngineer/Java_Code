package com.RolesAuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.RolesAuth.entity.User;
import com.RolesAuth.repository.UserRepository;

@RestController
public class HomeController {
	
	@Autowired
	private UserRepository repo;
	
	@PostMapping("/registers")
	public String register(@RequestBody User olduser) {
		User newuser=new User();
		newuser.setUsername(olduser.getUsername());
		newuser.setEmail(olduser.getEmail());
		newuser.setPassword(olduser.getPassword());
		newuser.setRoles(olduser.getRoles());
		repo.save(newuser);
		return "user is saved";
	}
	
	@GetMapping("/profile")
	public String showprofie() {
		return "just view Profile";
	}
	
	@GetMapping("/edit")
	public String edit() {
		return "just for edit";
	}
}
