package com.eprocurement.purchaseorder;

import java.util.List;

import com.eprocurement.quotation.Quotation;
import com.eprocurement.quotation.QuotationItem;

public interface PurchaseOrderService {
	
	public PurchaseOrder createNewPurchaseOrder(Quotation quotation);
	public void addPurchaseOrderItems(PurchaseOrder purchaseOrder,List<QuotationItem> items);
}
