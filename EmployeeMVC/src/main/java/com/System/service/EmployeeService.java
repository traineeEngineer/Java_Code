package com.System.service;

import java.util.List;

import com.System.entity.Employee;

public interface EmployeeService {
	
	List<Employee> getAllEmployee();

	void saveEmployee(Employee employee);

	Employee getEmployeeById(Integer id);

	void deleteEmployeeById(Integer id);
}
