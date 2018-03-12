package com.eprocurement.quotation;

import java.sql.Date;
import java.util.List;

import com.eprocurement.purchaserequest.PurchaseRequest;
import com.eprocurement.purchaserequest.PurchaseRequestItem;
import com.eprocurement.supplier.Supplier;

public interface QuotationService {
	
	public Quotation createNewQuotation(PurchaseRequest purchaseRequest, Supplier supplier);
	public void updateQuotation(Quotation quotation, Supplier supplier, Date date);
	public void addQuotationItems(Quotation quotation, List<PurchaseRequestItem> items);
}
