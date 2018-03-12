package com.eprocurement.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	//show all suppliers
	@GetMapping
	public String showDefaultSuppliers() {
		return "redirect:/supplier/all";
	}
	
	@GetMapping("/all")
	public String showAllSuppliers() {
		return "suppliers";
	}
	
	//get supplier form
	@GetMapping("/new")
	public String getSupplierForm(Model model) {
		model.addAttribute("supplier",new Supplier());
		return "supplier";
	}
	
	//save new supplier
	@PostMapping("/save")
	public String saveNewSupplier(@ModelAttribute Supplier supplier) {
		supplierRepository.save(supplier);
		return "redirect:/supplier";
	}
	
	//get supplier form for update
	@GetMapping("/{id}")
	public String getSupplierUpdateForm(@PathVariable Long id, Model model) {
		model.addAttribute("supplier",supplierRepository.findOne(id));
		return "supplier";
	}
	
}
