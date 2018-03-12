package com.eprocurement.web;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eprocurement.domain.PurchaseRequest;
import com.eprocurement.domain.Quotation;
import com.eprocurement.domain.QuotationRepository;
import com.eprocurement.domain.Supplier;
import com.eprocurement.service.QuotationService;

@RestController
public class QuotationRestController {

	@Autowired
	private QuotationRepository quotationRepository;
	
	@Autowired
	private QuotationService quotationService;
	
	@GetMapping("/api/pr/{pr}/quotation")
	public Page<Quotation> getQuotations(@PathVariable PurchaseRequest pr, Pageable pageable){
		return quotationRepository.findByPurchaseRequest(pr, pageable);
	}
	
	@PostMapping("/api/quotation/{quotation}")
	public void updateQuotation(@PathVariable Quotation quotation, 
			@RequestParam(value="supplier.id") Supplier supplier,
			@RequestParam Date quotationDate) {
		quotationService.updateQuotation(quotation, supplier, quotationDate);
	}
}
