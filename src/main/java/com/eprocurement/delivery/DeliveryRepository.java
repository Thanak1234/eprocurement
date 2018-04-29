package com.eprocurement.delivery;

import java.sql.Date;
import java.util.List;

import com.eprocurement.purchaseorder.PurchaseOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DeliveryRepository extends PagingAndSortingRepository<Delivery, String> {

	Page<Delivery> findByPurchaseOrder(PurchaseOrder po, Pageable pageable);
	List<Delivery> findByDateBetween (Date startDate, Date endDate);
}
