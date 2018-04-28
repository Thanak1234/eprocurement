package com.eprocurement.quotation;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.eprocurement.purchaserequest.PurchaseRequest;
import com.eprocurement.purchaserequest.PurchaseRequestItem;
import com.eprocurement.supplier.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QuotationServiceImpl implements QuotationService {

	@Autowired
	private QuotationRepository quotationRepository;
	
	@Autowired
	private QuotationItemsRepository quotationItemRepository;
	
	@Override
	public Quotation createNewQuotation(PurchaseRequest purchaseRequest, Supplier supplier) {
		Quotation quotation = new Quotation();
		
		Calendar now = Calendar.getInstance();
		
		String count = Long.toString(quotationRepository.count()+1);
		String id = "Q"+Integer.toString(now.get(Calendar.YEAR))+"-";
		
		for(int i=count.length();i < 4;i++) {
			id += "0";
		}
		
		id += count;
		quotation.setId(id);
		//Date now = new Date(Calendar.getInstance().getTimeInMillis());
		
		Date quotationDate = new Date(now.getTimeInMillis());
		quotation.setQuotationDate(quotationDate);
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

	@Override
	public void saveQuotationItems(List<QuotationItem> items, List<Double> price) {
		for (int i = 0; i < items.size(); i++) {
			items.get(i).setPrice(price.get(i));
		quotationItemRepository.save(items.get(i));
		}
	}

	
}
