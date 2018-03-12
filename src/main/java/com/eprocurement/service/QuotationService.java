package com.eprocurement.service;

import java.sql.Date;
import java.util.List;
import com.eprocurement.domain.PurchaseRequest;
import com.eprocurement.domain.PurchaseRequestItem;
import com.eprocurement.domain.Quotation;
import com.eprocurement.domain.Supplier;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface QuotationService {
	
	public Quotation createNewQuotation(PurchaseRequest purchaseRequest, Supplier supplier);
	public void updateQuotation(Quotation quotation, Supplier supplier, Date date);
	public void addQuotationItems(Quotation quotation, List<PurchaseRequestItem> items);
}
