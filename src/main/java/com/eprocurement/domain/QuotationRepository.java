package com.eprocurement.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuotationRepository extends PagingAndSortingRepository<Quotation, Long> {

	Page<Quotation> findByPurchaseRequest(PurchaseRequest pr, Pageable pageable);
	
}
