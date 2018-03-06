package com.eprocurement.service;

import java.util.List;

import com.eprocurement.domain.Delivery;
import com.eprocurement.domain.PurchaseOrder;
import com.eprocurement.domain.PurchaseOrderItem;

public interface DeliveryService {

	public Delivery createNewDelivery(PurchaseOrder purchaseOrder);
	public void addDeliveryItems(Delivery delivery, List<PurchaseOrderItem> items);
	
	
}
