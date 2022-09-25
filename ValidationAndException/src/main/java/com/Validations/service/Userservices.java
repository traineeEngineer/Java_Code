package com.Validations.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Validations.exceptions.UserNotFoundException;
import com.Validations.model.User;
import com.Validations.model.UserDto;
import com.Validations.repository.UserRepository;

@Service
public class Userservices {

	@Autowired
	private UserRepository repository;

	public User saveUser(UserDto userdto) {
          User user=new User();
          user.setName(userdto.getName());
          user.setAge(userdto.getAge());
          user.setEmail(userdto.getEmail());
          user.setGender(userdto.getGender());
          user.setMobile(userdto.getMobile());
          user.setNationality(userdto.getNationality());
          
          return repository.save(user);
	}
	
	public List<User> getAllUsers(){
		return repository.findAll();
	}
	
	public User getUser(Integer id) throws UserNotFoundException {
		Optional<User> user= repository.findById(id);
		if(user.isPresent()) {
			return user.get();
		}else {
			throw new  UserNotFoundException("user Not found");
		}
	}
}
