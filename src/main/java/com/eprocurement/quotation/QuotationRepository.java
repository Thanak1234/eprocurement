package com.eprocurement.quotation;

import java.sql.Date;
import java.util.List;

import com.eprocurement.purchaserequest.PurchaseRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuotationRepository extends PagingAndSortingRepository<Quotation, String> {

	Page<Quotation> findByPurchaseRequest(PurchaseRequest pr, Pageable pageable);
	List<Quotation> findByQuotationDateBetween(Date startDate, Date endDate);
}
