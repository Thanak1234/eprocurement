package com.eprocurement.delivery;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import com.eprocurement.purchaseorder.PurchaseOrder;
import com.eprocurement.purchaseorder.PurchaseOrderItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
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

	@Override
	public void saveItems(List<DeliveryItem> items, List<Integer> quantity) {
		for (int i = 0; i < items.size(); i++) {
			items.get(i).setQuantity(quantity.get(i));
			deliveryItemRepository.save(items.get(i));
		}
	}

	@Override
	public void updateDelivery(Delivery delivery, Date date) {
		delivery.setDate(date);
		deliveryRepository.save(delivery);
	}

}
