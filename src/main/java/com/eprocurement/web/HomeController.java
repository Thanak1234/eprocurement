package com.eprocurement.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String getIndexPage() {
		return "index";
	}

	@GetMapping("/403")
	public String accessDenied() {
		return "403";
	}
}
