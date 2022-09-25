package com.japAuth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.japAuth.model.Person;
import com.japAuth.repository.PersonRepository;

@Service
public class CustomPersonDetailService implements UserDetailsService {

	@Autowired
	private PersonRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Person person = repo.findByUsername(username);
		CustomPersonDetail detail = null;
		if (person != null) {
			detail = new CustomPersonDetail();
			detail.setPerson(person);
		}else {
			throw new UsernameNotFoundException("user name not found ");
		}
		return detail;
	}

}
