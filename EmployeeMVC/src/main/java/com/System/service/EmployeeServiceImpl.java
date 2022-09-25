package com.System.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.System.entity.Employee;
import com.System.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository repo;
	
	
	@Override
	public List<Employee> getAllEmployee() {
		return repo.findAll();
	}

	@Override
	public void saveEmployee(Employee employee) {
		this.repo.save(employee);
		
	}

	@Override
	public Employee getEmployeeById(Integer id) {
		  Optional<Employee> optional= repo.findById(id);
		  Employee emp=null;
		  if(optional.isPresent()) {
			  emp=optional.get();
		  }else {
			  throw new RuntimeException("Employee not found "+id);
		  }
		return emp;
	}

	@Override
	public void deleteEmployeeById(Integer id) {
		this.repo.deleteById(id);
		
	}

}
