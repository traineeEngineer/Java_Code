package com.japAuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.japAuth.model.Person;
import com.japAuth.repository.PersonRepository;

@RestController
@RequestMapping("/secure/auth")
public class AdminController {
	
	@Autowired
	private PasswordEncoder encodePwd;
	
	@Autowired
	private PersonRepository repos;
	
	@PostMapping("/admin/add")
	public String addPerson(@RequestBody Person person) {
     String pwd	=person.getPassword();
     String ecrypt=encodePwd.encode(pwd);
     person.setPassword(ecrypt);
     repos.save(person);
     return "person is added successful";
	}
}
