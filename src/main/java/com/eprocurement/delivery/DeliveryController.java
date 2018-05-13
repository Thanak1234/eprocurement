package com.eprocurement.delivery;

import com.eprocurement.purchaseorder.PurchaseOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeliveryController {

	@Autowired
	private DeliveryService deliveryService;

	@GetMapping("/po/{po}/delivery/all")
	public String getDeliveries(@PathVariable PurchaseOrder po, Model model) {
		return "deliveries";
	}

	@PostMapping("/po/{po}/delivery/new")
	public String createNewDelivery(@PathVariable PurchaseOrder po, @RequestParam String invoiceNumber) {
		Delivery delivery = deliveryService.createNewDelivery(po,invoiceNumber);
		return "redirect:/delivery/" + delivery.getId() + "/details";
	}

	@GetMapping("/delivery/{delivery}/details")
	public String getDeliveryDetails(@PathVariable Delivery delivery, Model model) {
		model.addAttribute("delivery", delivery);
		return "delivery";
	}

}
