package com.eprocurement.domain;

import java.sql.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PurchaseOrderRepository extends PagingAndSortingRepository<PurchaseOrder, Long> {
	
	Page<PurchaseOrder> findByDateBetween(Date startDate, Date endDate, Pageable pageable);
	Page<PurchaseOrder> findByPoNo(Long poNo, Pageable pageable);
	Page<PurchaseOrder> findByQuotation_PurchaseRequest_PrNo(String pr, Pageable pageable);
 }
