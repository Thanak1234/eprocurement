package com.eprocurement.service;

import java.util.List;

import com.eprocurement.domain.PurchaseOrder;
import com.eprocurement.domain.Quotation;
import com.eprocurement.domain.QuotationItem;

public interface PurchaseOrderService {
	
	public PurchaseOrder createNewPurchaseOrder(Quotation quotation);
	public void addPurchaseOrderItems(PurchaseOrder purchaseOrder,List<QuotationItem> items);
	
}
