package com.eprocurement.purchaseorder;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import com.eprocurement.quotation.Quotation;
import com.eprocurement.quotation.QuotationItem;
import com.eprocurement.utilities.IdGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;
	
	@Autowired
	private PurchaseOrderItemRepository purchaseOrderItemRepository;
	
	@Autowired
	private IdGenerator idGenerator;

	@Override
	public PurchaseOrder createNewPurchaseOrder(Quotation quotation) {
		Calendar now = Calendar.getInstance();
		//Start date and end date
		String startDateString = Integer.toString(now.get(Calendar.YEAR))+"-1"+"-1";
		String endDateString = Integer.toString(now.get(Calendar.YEAR))+"-12-31";
		Date startDate = Date.valueOf(startDateString);
		Date endDate = Date.valueOf(endDateString);
		//count
		String count = Integer.toString(purchaseOrderRepository.findByDateBetween(startDate, endDate).size()+1);
		String poNo = "PO" + idGenerator.createId(now, count);
	
	
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setPoNo(poNo);
		//Save purchase order
		Date poDate = new Date(now.getTimeInMillis());
		purchaseOrder.setDate(poDate);
		purchaseOrder.setQuotation(quotation);
		purchaseOrder.setStatus("Pending");
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
