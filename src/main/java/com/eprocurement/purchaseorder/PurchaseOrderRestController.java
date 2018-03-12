package com.eprocurement.purchaseorder;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseOrderRestController {

	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;
	
	//TODO move this to service layer
	@GetMapping("/api/po/all")
	public Page<PurchaseOrder> getAllPurchaseOrders(Pageable pageable,
			@RequestParam(defaultValue="") String searchBy,
			@RequestParam Date startDate, @RequestParam Date endDate,
			@RequestParam(defaultValue="") String searchValue){
		
		Page<PurchaseOrder> poPage;
	
		switch (searchBy) {
		case "prNo":
			poPage = purchaseOrderRepository.findByQuotation_PurchaseRequest_PrNo(searchValue, pageable);
			break;
		default:
			poPage = purchaseOrderRepository.findByDateBetween(startDate, endDate, pageable);
			break;
		}	
		return poPage;
	}
	
	@PostMapping("/api/po/{po}")
	public void savePurchaseOrder(@PathVariable PurchaseOrder po,@RequestParam Date date ) {
		po.setDate(date);
		purchaseOrderRepository.save(po);
	}
}
