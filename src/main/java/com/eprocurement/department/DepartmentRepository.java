package com.eprocurement.department;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DepartmentRepository extends PagingAndSortingRepository<Department, Long> {
	
	Page<Department> findByDepartmentNameStartingWith(String departmentName, Pageable pageable);
	Page<Department> findByDepartmentHeadStartingWith(String departmentHead, Pageable pageable);
}
