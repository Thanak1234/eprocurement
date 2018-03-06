package com.eprocurement.service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eprocurement.domain.PurchaseOrder;
import com.eprocurement.domain.PurchaseOrderItem;
import com.eprocurement.domain.PurchaseOrderItemRepository;
import com.eprocurement.domain.PurchaseOrderRepository;
import com.eprocurement.domain.Quotation;
import com.eprocurement.domain.QuotationItem;

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
