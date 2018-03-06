package com.eprocurement.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eprocurement.domain.PurchaseRequestItem;
import com.eprocurement.domain.Quotation;
import com.eprocurement.domain.QuotationItem;
import com.eprocurement.domain.QuotationItemsRepository;
import com.eprocurement.service.QuotationServiceImpl;

@RestController
@RequestMapping("/api/quotationitems")
public class QuotationItemRestController {

	@Autowired
	private QuotationItemsRepository quotationItemsRepository;
	
	@Autowired
	private QuotationServiceImpl quotationService;
	
	//add items to quotation
	@PostMapping("/add")
	public void addQuotationItems(@RequestParam List<PurchaseRequestItem> purchaseRequestItem, @RequestParam Quotation quotation){	
		quotationService.addQuotationItems(quotation, purchaseRequestItem);
	}
	
	@GetMapping("/{quotation}")
	public Page<QuotationItem> getQuotationItems(@PathVariable Quotation quotation, Pageable pageable){
		return quotationItemsRepository.findByQuotation(quotation, pageable);
	}
	
	@PostMapping("/delete")
	public void deleteQuotationItem(@RequestParam QuotationItem quotationItem) {
		quotationItemsRepository.delete(quotationItem);	
	}
	
	@PostMapping("/save")
	public void saveQuotationItems(@RequestParam List<QuotationItem> item ,@RequestParam List<Double> price) {
		for(int i=0;i < item.size();i++) {
			item.get(i).setPrice(price.get(i));
			quotationItemsRepository.save(item);
		}
	}
}
