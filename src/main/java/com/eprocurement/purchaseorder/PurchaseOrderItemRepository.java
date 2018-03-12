package com.eprocurement.purchaseorder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PurchaseOrderItemRepository extends PagingAndSortingRepository<PurchaseOrderItem, Long> {

		Page<PurchaseOrderItem> findByPurchaseOrder(PurchaseOrder po, Pageable pageable);
}
