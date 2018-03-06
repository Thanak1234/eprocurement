package com.eprocurement.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PurchaseRequestItemsRepository extends PagingAndSortingRepository<PurchaseRequestItem, Long> {
	
	Page<PurchaseRequestItem> findByPurchaseRequest(PurchaseRequest purchaseRequest, Pageable pageable);

}
