package com.eprocurement.quotation;

import com.eprocurement.purchaserequest.PurchaseRequest;
import com.eprocurement.supplier.Supplier;
import com.eprocurement.supplier.SupplierRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuotationController {
	
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	@Autowired
	private QuotationService quotationService;
	
	@PostMapping("/pr/{pr}/quotation/new")
	public String saveNewQuotation(@PathVariable PurchaseRequest pr, @RequestParam Supplier quotationSupplier) {
		Quotation quotation = quotationService.createNewQuotation(pr, quotationSupplier);
		return "redirect:/quotation/"+quotation.getId()+"/details";
	}
	
	//get all quotations
	@GetMapping("/pr/{pr}/quotation/all")
	public String getQuotations(@PathVariable PurchaseRequest pr, Model model) {
		model.addAttribute("suppliers",supplierRepository.findAll());
		return "prquotations";
	}
	
	@GetMapping("/quotation/{quotation}/details")
	public String getQuotationItemsForm(@PathVariable Quotation quotation, Model model) {
		model.addAttribute("suppliers",supplierRepository.findAll());
		return "quotationDetails";
	}
}
