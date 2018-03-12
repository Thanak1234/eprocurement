package com.eprocurement.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@GetMapping
	public String getDefaultDepartments() {
		return "redirect:/department/all";
	}
	
	@GetMapping("/*")
	public String getFallbackDepartments() {
		return "redirect:/department/all";
	}
	
	//show all department
	@GetMapping("/all")
	public String getDepartments() {
		return "departments";
	}
	
	//get department form
	@GetMapping("/new")
	public String getDepartmentForm(Model model) {
		model.addAttribute("department", new Department());
		return "department";
	}
	
	//save new department
	@PostMapping("/save")
	public String saveNewDepartment(@ModelAttribute Department department) {
		departmentRepository.save(department);
		return "redirect:/department";
	}
	
	//get update form
	@GetMapping("/{id}")
	public String getUpdateDepartmentForm(@PathVariable Long id, Model model) {
		model.addAttribute("department",departmentRepository.findOne(id));
		return "department";
	}
	
	

}
