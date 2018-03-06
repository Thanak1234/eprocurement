package com.eprocurement.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eprocurement.domain.Item;

import com.eprocurement.domain.ItemRepository;

@Controller
@RequestMapping("/item")
public class ItemController {
	@Autowired
	private ItemRepository itemRepository;
	
	@GetMapping
	public String showDefaultItemsPage() {
		return "redirect:/item/all";
	}
	
	@GetMapping("/all")
	public String showAllItems(){
		return "items";
	}

	//get item form
	@GetMapping("/new")
	public String getItemForm(Model model) {
		model.addAttribute("item", new Item());
		return "item";
	}

	//save new item
	@PostMapping("/save")
	public String createNewItem(@ModelAttribute Item item) {
		itemRepository.save(item);
		return "redirect:/item/all";
	}

	//get item form for update
	@GetMapping("/{id}")
	public String getUpdateForm(@PathVariable Long id, Model model){
		model.addAttribute("item", itemRepository.findOne(id));
		return "item";
	}

}
