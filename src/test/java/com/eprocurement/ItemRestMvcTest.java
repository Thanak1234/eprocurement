package com.eprocurement;

import com.eprocurement.item.Item;
import com.eprocurement.item.ItemRepository;
import com.eprocurement.item.ItemRestController;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;

/**
 * ItemRestMvcTest
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ItemRestController.class)
@WithMockUser(roles = "ADMIN")
@EnableSpringDataWebSupport
public class ItemRestMvcTest {

    @MockBean
    private ItemRepository itemRepository;

    @Autowired
    private MockMvc mvc;

    @Test
    public void testGetItems() throws Exception {
        //Set up
        Item newItem = new Item();
        newItem.setName("Shoes");
        newItem.setDescription("Vans");
        Pageable pageable = PageRequest.of(0, 20);
        List<Item> items = new ArrayList<>();
        items.add(newItem);
        Page<Item> itemPage = new PageImpl<Item>(items, pageable, 20);

        given(itemRepository.findAll(any(Pageable.class))).willReturn(itemPage);
        this.mvc.perform(get("/api/items").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].name", is(newItem.getName())));

    }
}