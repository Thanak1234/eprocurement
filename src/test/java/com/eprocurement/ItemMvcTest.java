package com.eprocurement;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.eprocurement.item.Item;
import com.eprocurement.item.ItemRepository;
import com.eprocurement.item.ItemRestController;

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
@WebMvcTest(ItemRestController.class)
public class ItemMvcTest {

	
	
	@Autowired
	private MockMvc mvc;
	
	/*
	 * Mock Dependencies and Setup test
	 */
	@MockBean
	private ItemRepository itemRepository;
	
	@MockBean
	private Item item;

	@Before
	public void setup() {	
		item.setDescription("black");
		item.setName("shoes");
	}
	
	//Test get item management
	@Test
	@WithMockUser("admin")
	public void testManageItem()throws Exception{
		mvc.perform(get("/item"))
		.andExpect(status().isOk())
		.andExpect(view().name("items"));
	}
	
	//Test for get item form
	@Test
	@WithMockUser("admin")
	public void testGetItemForm() throws Exception{ 
		mvc.perform(get("/item/new"))
			.andExpect(status().isOk())
			.andExpect(view().name("item"))
			.andExpect(model().attributeExists("item"))
			.andExpect(model().attributeExists("pageTitle")) 
			.andExpect(model().attribute("pageTitle", "New Item"));//Page title should be New Item
	}
	//Test for saving item
	@Test
	@WithMockUser("admin")
	public void testSaveNewItem() throws Exception{
		mvc.perform(post("/item/new"))		
		.andExpect(status().is3xxRedirection())
		.andExpect(header().string("Location","/item"));
	}
	
	//Test for get item update form
	@Test
	@WithMockUser("admin")
	public void testGetItemUpdateForm()throws Exception{
		given(itemRepository.findById((long) 1).get()).willReturn(item);	
		mvc.perform(get("/item/update/1"))
		.andExpect(status().isOk())
		.andExpect(view().name("item"))
		.andExpect(model().attributeExists("item"))
		.andExpect(model().attribute("item", equalTo(item)))
		.andExpect(model().attributeExists("pageTitle"))  //for dynamic page title
		.andExpect(model().attribute("pageTitle", "Update item 1")); //Page title should be Update item 1
	}
	
	//Test for save item update 
	@Test
	@WithMockUser("admin")
	public void testSaveUpdate()throws Exception{
		mvc.perform(post("/item/update/1"))		
		.andExpect(status().is3xxRedirection())
		.andExpect(header().string("Location","/item"));
	}
	

	
	}
	
	
	

