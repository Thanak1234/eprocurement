package com.eprocurement.purchaseorder;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import com.eprocurement.quotation.Quotation;
import com.eprocurement.quotation.QuotationItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;
	
	@Autowired
	private PurchaseOrderItemRepository purchaseOrderItemRepository;
	
	@Override
	public PurchaseOrder createNewPurchaseOrder(Quotation quotation) {
		Calendar now = Calendar.getInstance();
		String poNo= "PO"+Integer.toString(now.get(Calendar.YEAR))+"-";
		String count = Long.toString(purchaseOrderRepository.count()+1);
		for(int i=count.length();i<4;i++) {
			poNo +=0;
		}
		poNo += count;
		//Date now = new Date(Calendar.getInstance().getTimeInMillis());
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setPoNo(poNo);
		//Save purchase order
		Date poDate = new Date(now.getTimeInMillis());
		purchaseOrder.setDate(poDate);
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
