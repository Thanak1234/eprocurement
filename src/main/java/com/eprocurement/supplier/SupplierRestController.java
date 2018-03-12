package com.eprocurement.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SupplierRestController {
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	//TODO move this to service layer
	@GetMapping("/api/suppliers")
	public @ResponseBody Page<Supplier> getAllSuppliers(Pageable pageable,
			@RequestParam(defaultValue="") String searchBy, @RequestParam(defaultValue="") String searchValue) {
		
		Page<Supplier> supplierPage;
		
			switch (searchBy) {
			case "supplierName":
				supplierPage = supplierRepository.findBySupplierNameStartingWith(searchValue, pageable);
				break;
			case "address":
				supplierPage = supplierRepository.findByAddressStartingWith(searchValue, pageable);
				break;
			default:
				supplierPage = supplierRepository.findAll(pageable);
				break;
			}
			
		return supplierPage;
	}
	
}
