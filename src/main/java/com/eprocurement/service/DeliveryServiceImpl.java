package com.eprocurement.service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eprocurement.domain.Delivery;
import com.eprocurement.domain.DeliveryItem;
import com.eprocurement.domain.DeliveryItemRepository;
import com.eprocurement.domain.DeliveryRepository;
import com.eprocurement.domain.PurchaseOrder;
import com.eprocurement.domain.PurchaseOrderItem;

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
