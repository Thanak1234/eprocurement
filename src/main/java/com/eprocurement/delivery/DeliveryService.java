package com.eprocurement.delivery;

import java.util.List;

import com.eprocurement.purchaseorder.PurchaseOrder;
import com.eprocurement.purchaseorder.PurchaseOrderItem;

public interface DeliveryService {

	public Delivery createNewDelivery(PurchaseOrder purchaseOrder);
	public void addDeliveryItems(Delivery delivery, List<PurchaseOrderItem> items);
	
	
}
