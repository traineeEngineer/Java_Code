package com.InememoryAuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	

	@Autowired
	private PasswordEncoder bcryptencode;
	
	
	@GetMapping("/home")
	public String home() {
		return "this is home page";
	}

	@GetMapping("/dashboard")
	public String dashboard() {
		return "you seeing the dashboad content";
	}
}
