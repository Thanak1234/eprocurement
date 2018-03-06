package com.eprocurement.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuotationItemsRepository extends PagingAndSortingRepository<QuotationItem, Long> {

	Page<QuotationItem> findByQuotation(Quotation quotation, Pageable pageable);
}
