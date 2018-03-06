package com.eprocurement.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DeliveryRepository extends PagingAndSortingRepository<Delivery, Long>{

	Page<Delivery> findByPurchaseOrder(PurchaseOrder po, Pageable pageable);
}
