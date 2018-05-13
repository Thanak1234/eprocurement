package com.eprocurement.delivery;

import java.sql.Date;
import java.util.List;

import com.eprocurement.purchaseorder.PurchaseOrder;
import com.eprocurement.purchaseorder.PurchaseOrderItem;

public interface DeliveryService {

	public Delivery createNewDelivery(PurchaseOrder purchaseOrder, String invoiceNumber);
	public void addDeliveryItems(Delivery delivery, List<PurchaseOrderItem> items);
	public void saveItems(List<DeliveryItem> items,List<Integer>quantity);
	public void updateDelivery(Delivery delivery, Date date, String invoiceNumeber);
}
