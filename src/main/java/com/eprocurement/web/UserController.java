package com.eprocurement.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.eprocurement.domain.DepartmentRepository;
import com.eprocurement.domain.User;
import com.eprocurement.domain.UserRepository;
import com.eprocurement.service.UserServiceImpl;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@GetMapping("/register")
	public String getRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("departments", departmentRepository.findAll());
		return "user";
	}
	
	@PostMapping("/user/save")
	public String registerNewUser(@ModelAttribute User user) {
		userService.registerUser(user);	
		return "redirect:/user/all";
	}
	
	@GetMapping("/user/{user}")
	public String getUpdateUserForm(@PathVariable User user, Model model) {
		model.addAttribute("user",userRepository.findOne(user.getId()));
		model.addAttribute("departments", departmentRepository.findAll());
		return "user";
	}
	
	@GetMapping("/user/all")
	public String getAllUsers() {
		return "users";
	}	
}

