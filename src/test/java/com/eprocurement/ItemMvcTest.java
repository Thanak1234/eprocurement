package com.eprocurement;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import com.eprocurement.item.Item;
import com.eprocurement.item.ItemController;
import com.eprocurement.item.ItemRepository;

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
@WebMvcTest(ItemController.class)
@WithMockUser(roles = "ADMIN")
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
	public void testManageItem() throws Exception {
		mvc.perform(get("/item/all")).andExpect(status().isOk());

	}

	//Test for get item form
	@Test
	public void testGetItemForm() throws Exception {
		mvc.perform(get("/item/new")).andExpect(status().isOk()).andExpect(view().name("item"))
				.andExpect(model().attributeExists("item"));

	}

	//Test for saving item
	@Test
	public void testSaveNewItem() throws Exception {
		mvc.perform(post("/item/save").with(csrf())).andExpect(status().is3xxRedirection())
				.andExpect(header().string("Location", "/item/all"));
		;

	}

	//Test for get item update form
	@Test
	@WithMockUser(roles = "ADMIN")
	public void testGetItemUpdateForm() throws Exception {
		
		given(this.itemRepository.findById(1L)).willReturn(Optional.of(item));
		
		mvc.perform(get("/item/1")).andExpect(status().isOk()).andExpect(view().name("item"))
				.andExpect(model().attributeExists("item")).andExpect(model().attribute("item", equalTo(item)));
	}

}
