package com.MVC.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.MVC.model.Student;
import com.MVC.service.Studentservice;

@Controller
public class StudentController {
	
	@Autowired
	private Studentservice  service;
	
	
	@GetMapping("/students")
	public String listStudent(Model model){
		model.addAttribute("students",service.getAllStudents());
		return "students";
	}
    
	@GetMapping("/students/new")
	public String createStudentForm(Model model) {
		Student stu=new Student();
		model.addAttribute("student", stu);
		return "create_student";
	}
	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student")  Student student) {
		service.saveStudent(student);
		return "redirect:/students";
	}
	
	@GetMapping("/students/edit/{id}")
	public String editStudent(@PathVariable Long id,Model model) {
		model.addAttribute("student", service.getStudentById(id));
	   return "edit_student";	
	}
	
	
	@PostMapping("/students/{id}")
	public String  updateStudent(@PathVariable Long id,@ModelAttribute("student") Student student,Model model) {
		Student existingStudent=service.getStudentById(id);
		existingStudent.setId(id);
        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setAddress(student.getAddress());
        
        service.updateStudent(existingStudent);
        return "redirect:/students"; 
		
	}
	
	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable Long id) {
		service.getStudentById(id);
		return "redirect:/students";
	}
	
}
