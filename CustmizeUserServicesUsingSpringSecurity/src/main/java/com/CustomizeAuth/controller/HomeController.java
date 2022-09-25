package com.CustomizeAuth.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.CustomizeAuth.entity.UserModel;
import com.CustomizeAuth.entity.Userinfo;
import com.CustomizeAuth.repository.UserInfoRepository;

@RestController
public class HomeController {

	@Autowired
	private UserInfoRepository repo;

	@Autowired
	private PasswordEncoder encoder;

	@GetMapping("/home")
	public String home() {
		return "come back to home kodakaa!";
	}

	@GetMapping("/dashboard")
	public String dashboard() {
		return "dashborad for see details";
	}

	@PostMapping("/register")
	public Userinfo register(@RequestBody UserModel model) {
		Userinfo newUser=new Userinfo();
		newUser.setEmail(model.getEmail());
		newUser.setPassword(encoder.encode(model.getPassword()));
		return repo.save(newUser);
	}
	
	@GetMapping("/profile")
	public String profile() {
		return "This is profile content";
	}

}
