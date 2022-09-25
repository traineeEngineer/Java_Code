package com.CustomizeAuth.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.CustomizeAuth.entity.Userinfo;
import com.CustomizeAuth.repository.UserInfoRepository;

@Service
public class CustomeUserDeatilService implements UserDetailsService {

	@Autowired
	private UserInfoRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Userinfo userinfo=repo.findByEmail(email)
				          .orElseThrow(()->new UsernameNotFoundException("user not found"));
		return new User(userinfo.getEmail(), userinfo.getPassword(), new ArrayList<>());
	}

}
