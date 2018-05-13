package com.eprocurement.delivery;

import java.sql.Date;

import com.eprocurement.purchaseorder.PurchaseOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeliveryRestController {
	
	@Autowired
	private DeliveryRepository deliveryRepository;

	@Autowired
	private DeliveryService deliveryService;
	
	@GetMapping("/api/po/{po}/delivery")
	public Page<Delivery> getDeliveries(@PathVariable PurchaseOrder po, Pageable pageable){
		return deliveryRepository.findByPurchaseOrder(po, pageable);
	}

	@PostMapping("/api/delivery/{delivery}")
	public void updateDelivery(@PathVariable Delivery delivery, @RequestParam Date date, @RequestParam String invoiceNumber){
		deliveryService.updateDelivery(delivery, date,invoiceNumber);
	}

}
