package com.eprocurement.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentRestController {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@GetMapping("/api/departments")
	public Page<Department> getAllDepartments(Pageable pageable,
			@RequestParam(defaultValue="") String searchBy, @RequestParam(defaultValue="") String searchValue){
		
		Page<Department> page;
		switch(searchBy) {
		case "departmentName":
			page = departmentRepository.findByDepartmentNameStartingWith(searchValue, pageable);
			break;
		case "departmentHead":
			page = departmentRepository.findByDepartmentHeadStartingWith(searchValue, pageable);
			break;
		default:
			page = departmentRepository.findAll(pageable);
			break;
		}
		
		return page;
	}
}
