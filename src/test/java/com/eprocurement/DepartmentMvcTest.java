package com.eprocurement;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import com.eprocurement.department.Department;
import com.eprocurement.department.DepartmentController;
import com.eprocurement.department.DepartmentRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(DepartmentController.class)
@WithMockUser(roles="ADMIN")
public class DepartmentMvcTest {
	
	@Autowired
	private MockMvc mvc;
	
	/*
	 * Set up test
	 */
	
	@MockBean
	private DepartmentRepository departmentRepository;
	
	@MockBean
	private Department department;
	
	//test get all department
	@Test
	public void testGetAllDepartments()throws Exception{
		mvc.perform(get("/department/all"))
			.andExpect(status().isOk())
			.andExpect(view().name("departments"));
	}
	
	//test get update form
	@Test
	public void testGetDepartmentForm()throws Exception{
		mvc.perform(get("/department/new"))
			.andExpect(status().isOk())
			.andExpect(view().name("department"))
			.andExpect(model().attributeExists("department"));
	}
	
	//test save department
	@Test
	public void testSaveNewDepartment()throws Exception{
		mvc.perform(post("/department/save").with(csrf()))
			.andExpect(status().is3xxRedirection())
			.andExpect(header().string("Location","/department/all"));
	}
	
	//test get update form
	@Test
	public void testGetUpdateForm()throws Exception{
		
		Department department = new Department();
		department.setId(1L);
		department.setDepartmentName("MPDO");
		department.setDepartmentHeadPosition("MPDC");
		department.setDepartmentHead("NA");
			
		given(departmentRepository.findById(1L)).willReturn(Optional.of(department));
		
		mvc.perform(get("/department/1"))
			.andExpect(status().isOk())
			.andExpect(view().name("department"))
			.andExpect(model().attributeExists("department"))
			.andExpect(model().attribute("department", equalTo(department)));
	}
	
}
