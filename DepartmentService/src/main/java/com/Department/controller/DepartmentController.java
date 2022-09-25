package com.Department.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Department.model.Department;
import com.Department.service.Departmentservice;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/departments")
@Slf4j
public class DepartmentController {

	@Autowired
	private Departmentservice service;
	
	
	@PostMapping("/")
	public Department saveDepartment(@RequestBody Department department) {
		log.info("inside save department department controller");
		return service.saveDepartment(department);
	}
	
	@GetMapping("/{id}")
	public Department findDepartmentById(@PathVariable(name = "id") Long departmentId) {
		log.info("find by id using department controller");
		return service.findDepartmentById(departmentId);
	}
}
