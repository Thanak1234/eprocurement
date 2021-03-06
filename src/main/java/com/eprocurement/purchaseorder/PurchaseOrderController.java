package com.eprocurement.purchaseorder;

import com.eprocurement.quotation.Quotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PurchaseOrderController {

	@Autowired
	private PurchaseOrderService purchaseOrderService;

	@GetMapping("/po")
	public String getDefaultManagePurchaseOrder() {
		return "redirect:/po/all";
	}
	
	@GetMapping("/po/all")
	public String getAllPurchaseOrder() {
		return "purchaseOrders";
	}
	
	@GetMapping("/quotation/{quotation}/po/new")
	public String getNewPurchaseOrderForm(@PathVariable Quotation quotation) {
		PurchaseOrder po = purchaseOrderService.createNewPurchaseOrder(quotation);
		return "redirect:/po/"+po.getPoNo()+"/details";
	}
		
	@GetMapping("/po/{po}/details")
	public String getPurchaseOrderDetailsForm(@PathVariable PurchaseOrder po) {
		return "purchaseOrderDetails";
	}
	
}
