package com.eprocurement.purchaseorder;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.eprocurement.quotation.Quotation;
import com.eprocurement.quotation.QuotationItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;
	
	@Autowired
	private PurchaseOrderItemRepository purchaseOrderItemRepository;
	
	@Override
	public PurchaseOrder createNewPurchaseOrder(Quotation quotation) {
		Date now = new Date(Calendar.getInstance().getTimeInMillis());
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setDate(now);
		purchaseOrder.setQuotation(quotation);
		purchaseOrderRepository.save(purchaseOrder);
		return purchaseOrder;
	}

	@Override
	public void addPurchaseOrderItems(PurchaseOrder purchaseOrder, List<QuotationItem> items) {
		for(QuotationItem item : items) {
			PurchaseOrderItem purchaseOrderItem = new PurchaseOrderItem();
			purchaseOrderItem.setPurchaseOrder(purchaseOrder);
			purchaseOrderItem.setQuotationItem(item);
			purchaseOrderItemRepository.save(purchaseOrderItem);
		}
		
	}

}
