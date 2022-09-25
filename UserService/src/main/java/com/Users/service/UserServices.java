package com.Users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.Users.VO.Department;
import com.Users.VO.ResponseTemplateVO;
import com.Users.model.User;
import com.Users.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServices {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RestTemplate restTemplate;

	public User saveUser(User user) {
		log.info("inside saveuser of service");
		return userRepo.save(user);
	}

	public ResponseTemplateVO getUserWithDepartment(Long userId) {
		log.info("inside getUserWithDepartment  service");
		ResponseTemplateVO vo = new ResponseTemplateVO();
		User user = userRepo.findById(userId).get();
		Department department = restTemplate.getForObject("http://DEPARMENT-SERVICE/departments/" + user.getDepartmentId(),
				                Department.class);

		vo.setUser(user);
		vo.setDepartment(department);
		return vo; 
	}

}
