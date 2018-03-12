package com.eprocurement.delivery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DeliveryItemRepository extends PagingAndSortingRepository<DeliveryItem, Long>{

		Page<DeliveryItem> findByDelivery(Delivery delivery, Pageable pageable);
		
		
}
