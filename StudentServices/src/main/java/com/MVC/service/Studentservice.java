package com.MVC.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MVC.model.Student;
import com.MVC.repository.StudentRepository;

@Service
public class Studentservice {
	
	
	@Autowired
	private StudentRepository repository;

	public List<Student> getAllStudents() {
		return repository.findAll();
	}
	
	public Student saveStudent(Student student) {
		return repository.save(student);
	}
	
	public Student getStudentById(Long id) {
		return repository.findById(id).get();
	}
	
	public Student updateStudent(Student student) {
	   return repository.save(student);
	}
	
	public void deleStudentById(Long id) {
		repository.deleteById(id);
	}
	
}
