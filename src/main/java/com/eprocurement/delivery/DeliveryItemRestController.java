package com.eprocurement.delivery;

import java.util.List;

import com.eprocurement.purchaseorder.PurchaseOrder;
import com.eprocurement.purchaseorder.PurchaseOrderItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/deliveryitem")
public class DeliveryItemRestController {
	
	@Autowired
	private DeliveryItemRepository deliveryItemRepository;
	
	@Autowired
	private DeliveryService deliveryService;
	
	@GetMapping("/{delivery}")
	public Page<DeliveryItem> getDeliveryItems(@PathVariable Delivery delivery, Pageable pageable){
		return deliveryItemRepository.findByDelivery(delivery, pageable);
	}
	
	@PostMapping("/{delivery}/add")
	public void addDeliveryItems(@PathVariable Delivery delivery, @RequestParam List<PurchaseOrderItem> item) {
		deliveryService.addDeliveryItems(delivery, item);
	}
	
	@PostMapping("/save")
	public void saveItem(@RequestParam List<DeliveryItem> item, @RequestParam List<Integer> quantity) {
		deliveryService.saveItems(item, quantity);
	}
	
	@PostMapping("/delete")
	public void removeItem(@RequestParam DeliveryItem item) {
		deliveryItemRepository.delete(item);
	}

	@GetMapping("/po/{po}")
	public List<DeliveryItem> getDeliveryItemsByPoNumber(@PathVariable PurchaseOrder po){
		return deliveryItemRepository.findByDelivery_PurchaseOrder(po);
	}
}
