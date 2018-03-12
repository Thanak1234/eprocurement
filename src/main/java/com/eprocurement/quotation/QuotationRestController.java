package com.eprocurement.quotation;

import java.sql.Date;

import com.eprocurement.purchaserequest.PurchaseRequest;
import com.eprocurement.supplier.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
