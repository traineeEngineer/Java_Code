package com.japAuth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/auth")
public class AppController {
	
	
	@GetMapping("/process")
	public String process() {
		return "security is passed";
	}
}
