package com.eprocurement.delivery;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;


import com.eprocurement.purchaseorder.PurchaseOrder;
import com.eprocurement.purchaseorder.PurchaseOrderItem;
import com.eprocurement.utilities.IdGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DeliveryServiceImpl implements DeliveryService {

	@Autowired
	private DeliveryRepository deliveryRepository;
	
	@Autowired
	private DeliveryItemRepository deliveryItemRepository;

	@Autowired
	private IdGenerator idGenerator;
	
	@Override
	public Delivery createNewDelivery(PurchaseOrder purchaseOrder) {
		Calendar now = Calendar.getInstance();
		//start date and end date
		String startDateString = Integer.toString(now.get(Calendar.YEAR)) + "-1-1";
		String endDateString = Integer.toString(now.get(Calendar.YEAR)) + "-12-31";
		Date startDate = Date.valueOf(startDateString);
		Date endDate = Date.valueOf(endDateString);
		//count
		String count = Integer.toString(deliveryRepository.findByDateBetween(startDate, endDate).size()+1);
		String id = "D"+idGenerator.createId(now,count);

		Delivery delivery = new Delivery();
		delivery.setId(id);
		Date deliveryDate = new Date(now.getTimeInMillis());
		delivery.setDate(deliveryDate);
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
