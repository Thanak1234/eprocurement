package com.eprocurement;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.eprocurement.supplier.Supplier;
import com.eprocurement.supplier.SupplierController;
import com.eprocurement.supplier.SupplierRepository;

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
@WebMvcTest(SupplierController.class)
public class SupplierMvcTest {

	@Autowired
	private MockMvc mvc;
	
	/*
	 * Mock Dependencies and Setup test
	 */
	@MockBean 
	private SupplierRepository supplierRepository;
	
	@MockBean
	private Supplier supplier;
	
	@Before
	public void setup() {
		supplier.setSupplierName("ABC Company");
		supplier.setAddress("Bayombong, Nueva Vizcaya");
		supplier.setContactNumber("N/A");
	}
	
	
	//Test get supplier management
	@Test
	@WithMockUser("admin")
	public void testSupplierManagement()throws Exception{
		mvc.perform(get("/supplier"))
		.andExpect(status().isOk())
		.andExpect(view().name("suppliers"));
	}
	
	//MVC test for get supplier form
	@Test
	@WithMockUser("admin")
	public void testGetSupplierForm()throws Exception{
		mvc.perform(get("/supplier/new"))
			.andExpect(status().isOk())
			.andExpect(view().name("supplier"))
			.andExpect(model().attributeExists("supplier"))
			.andExpect(model().attributeExists("pageTitle"))
			.andExpect(model().attribute("pageTitle","New Supplier"));
		
	}
	
	//MVC test for saving new supplier
	@Test
	@WithMockUser("admin")
	public void testSaveNewSupplier()throws Exception{
		mvc.perform(post("/supplier/new"))
			.andExpect(status().is3xxRedirection())
			.andExpect(header().string("Location", "/supplier"));	
	}
	
	//MCV test for get supplier update form
	@Test
	@WithMockUser("admin")
	public void testGetSupplierUpdateForm()throws Exception{
		given(supplierRepository.findById((long) 1).get()).willReturn(supplier);
		mvc.perform(get("/supplier/update/1"))
		.andExpect(status().isOk())
		.andExpect(view().name("supplier"))
		.andExpect(model().attributeExists("supplier"))
		.andExpect(model().attribute("supplier", equalTo(supplier)))
		.andExpect(model().attributeExists("pageTitle"))
		.andExpect(model().attribute("pageTitle", "Update supplier 1"));
									
	}
	
	
	//MVC test for updating suppler
	@Test
	@WithMockUser("admin")
	public void testUpdateSupplier()throws Exception{
		mvc.perform(post("/supplier/update/1"))
			.andExpect(status().is3xxRedirection())
			.andExpect(header().string("Location", "/supplier"));
	}
	
}
