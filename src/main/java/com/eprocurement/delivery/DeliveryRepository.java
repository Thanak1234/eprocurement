package com.eprocurement.delivery;

import com.eprocurement.purchaseorder.PurchaseOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DeliveryRepository extends PagingAndSortingRepository<Delivery, Long> {

	Page<Delivery> findByPurchaseOrder(PurchaseOrder po, Pageable pageable);
}
