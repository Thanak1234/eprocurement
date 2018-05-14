package com.eprocurement.delivery;

import java.util.List;

import com.eprocurement.purchaseorder.PurchaseOrder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DeliveryItemRepository extends PagingAndSortingRepository<DeliveryItem, Long> {

	Page<DeliveryItem> findByDelivery(Delivery delivery, Pageable pageable);
	List<DeliveryItem> findByDelivery_PurchaseOrder(PurchaseOrder purchaseOrder);
}
