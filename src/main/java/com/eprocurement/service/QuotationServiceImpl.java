package com.eprocurement.service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eprocurement.domain.PurchaseRequest;
import com.eprocurement.domain.PurchaseRequestItem;
import com.eprocurement.domain.Quotation;
import com.eprocurement.domain.QuotationItem;
import com.eprocurement.domain.QuotationItemsRepository;
import com.eprocurement.domain.QuotationRepository;
import com.eprocurement.domain.Supplier;

@Service
public class QuotationServiceImpl implements QuotationService {

	@Autowired
	private QuotationRepository quotationRepository;
	
	@Autowired
	private QuotationItemsRepository quotationItemRepository;
	
	@Override
	public Quotation createNewQuotation(PurchaseRequest purchaseRequest, Supplier supplier) {
		Quotation quotation = new Quotation();
		Date now = new Date(Calendar.getInstance().getTimeInMillis());
		quotation.setQuotationDate(now);
		quotation.setPurchaseRequest(purchaseRequest);
		quotation.setSupplier(supplier);
		quotationRepository.save(quotation);
		return quotation;

	}

	@Override
	public void updateQuotation(Quotation quotation, Supplier supplier, Date date) {
		quotation.setSupplier(supplier);
		quotation.setQuotationDate(date);
		quotationRepository.save(quotation);
	}

	@Override
	public void addQuotationItems(Quotation quotation, List<PurchaseRequestItem> items) {
		for(PurchaseRequestItem item : items) {
			QuotationItem quotationItem = new QuotationItem();
			quotationItem.setPurchaseRequestItem(item);
			quotationItem.setPrice(0.0);
			quotationItem.setQuotation(quotation);
			quotationItemRepository.save(quotationItem);
		}
		
	}

	
}
