package com.System.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.System.entity.Employee;
import com.System.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("listEmployee", service.getAllEmployee());
		return "index";
	}

	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		Employee emp = new Employee();
		model.addAttribute("employee", emp);
		return "new_employee";
	}

	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee emp) {
		service.saveEmployee(emp);
		return "redirect:/";
	}

	@GetMapping("/showFormUpdate/{id}")
	public String showFormUpdate(@PathVariable Integer id, Model model) {
		Employee emp = service.getEmployeeById(id);
		model.addAttribute("employee", emp);
		return "update_employee";
	}

	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable Integer id){
		service.deleteEmployeeById(id);
		return "redirect:/";
	}
}
