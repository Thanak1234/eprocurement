package com.eprocurement;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.eprocurement.department.Department;
import com.eprocurement.department.DepartmentController;
import com.eprocurement.department.DepartmentRepository;

import org.junit.Before;
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
	
	@Before
	public void setup() {
		department.setDepartmentHead("Mock Bock");
		department.setDepartmentName("XYZ department");
	}
	
	//test get all department
	@Test
	@WithMockUser("admin")
	public void testGetAllDepartments()throws Exception{
		mvc.perform(get("/department"))
			.andExpect(status().isOk())
			.andExpect(view().name("departments"));
	}
	
	//test get update form
	@Test
	@WithMockUser("admin")
	public void testGetDepartmentForm()throws Exception{
		mvc.perform(get("/department/new"))
			.andExpect(status().isOk())
			.andExpect(view().name("department"))
			.andExpect(model().attributeExists("department"))
			.andExpect(model().attributeExists("pageTitle"))
			.andExpect(model().attribute("pageTitle", "New Department"));
	}
	
	//test save department
	@Test
	@WithMockUser("admin")
	public void testSaveNewDepartment()throws Exception{
		mvc.perform(post("/department/new"))
			.andExpect(status().is3xxRedirection())
			.andExpect(header().string("Location","/department"));
	}
	
	//test get update form
	@Test
	@WithMockUser("admin")
	public void testGetUpdateForm()throws Exception{
		given(departmentRepository.findOne((long) 1)).willReturn(department);
		mvc.perform(get("/department/update/1"))
			.andExpect(status().isOk())
			.andExpect(view().name("department"))
			.andExpect(model().attributeExists("department"))
			.andExpect(model().attribute("department", equalTo(department)))
			.andExpect(model().attributeExists("pageTitle"))
			.andExpect(model().attribute("pageTitle", "Update Department 1"));
	}
	
	//test update department
	@Test
	@WithMockUser("admin")
	public void testUpdateDepartment()throws Exception{
		mvc.perform(post("/department/update/1"))
			.andExpect(status().is3xxRedirection())
			.andExpect(header().string("Location", "/department"));
	}
}
