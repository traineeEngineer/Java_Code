package com.Department.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Department.model.Department;
import com.Department.repository.DepartmentRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Departmentservice {
	
	@Autowired
	private DepartmentRepository repo;

	public Department saveDepartment(Department department) {
		log.info("inside save department of service");
		return repo.save(department);
	}

	public Department findDepartmentById(Long departmentId) {
		log.info("inside findByid department service ");
		return repo.findById(departmentId).get();
	}
}
