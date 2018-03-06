package com.eprocurement.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SupplierRepository extends PagingAndSortingRepository<Supplier, Long> {

	Page<Supplier> findBySupplierNameStartingWith(String supplierName , Pageable pageable);
	Page<Supplier> findByAddressStartingWith(String address, Pageable pageable);
	
}
