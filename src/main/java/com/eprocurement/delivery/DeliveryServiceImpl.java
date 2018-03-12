package com.eprocurement.delivery;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.eprocurement.purchaseorder.PurchaseOrder;
import com.eprocurement.purchaseorder.PurchaseOrderItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryServiceImpl implements DeliveryService {

	@Autowired
	private DeliveryRepository deliveryRepository;
	
	@Autowired
	private DeliveryItemRepository deliveryItemRepository;
	
	@Override
	public Delivery createNewDelivery(PurchaseOrder purchaseOrder) {
		Date now = new Date(Calendar.getInstance().getTimeInMillis());
		Delivery delivery = new Delivery();
		delivery.setDate(now);
		delivery.setPurchaseOrder(purchaseOrder);
		deliveryRepository.save(delivery);
		return delivery;
	}

	@Override
	public void addDeliveryItems(Delivery delivery, List<PurchaseOrderItem> items) {
		for(PurchaseOrderItem item : items) {
			DeliveryItem deliveryItem = new DeliveryItem();
			deliveryItem.setDelivery(delivery);
			deliveryItem.setPurchaseOrderItem(item);
			deliveryItem.setQuantity(1);
			deliveryItemRepository.save(deliveryItem);
		}

	}

}
