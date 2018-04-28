package com.eprocurement.purchaserequest;


import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.eprocurement.department.Department;
import com.eprocurement.item.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class PurchaseRequestServiceImpl implements PurchaseRequestService {

	@Autowired
	private PurchaseRequestRepository purchaseRequestRepository;
	
	@Autowired
	private PurchaseRequestItemsRepository purchaseRequestItemRepository;
	
	@Override
	public void createNewPurchaseRequest(PurchaseRequest purchaseRequest) {
		Calendar now = Calendar.getInstance();	
		//purchase request number
		String count = Long.toString(purchaseRequestRepository.count()+1);
		String prNo = Integer.toString(now.get(Calendar.YEAR))+"-";
		for(int i=count.length();i<4;i++) {
			prNo += "0";
		}
		prNo +=count;
		purchaseRequest.setPrNo(prNo);
		//purchase request date
		Date prDate = new Date(now.getTimeInMillis());
		purchaseRequest.setPrDate(prDate);
		purchaseRequestRepository.save(purchaseRequest);
	}

	@Override
	public void updatePurchaseRequest(PurchaseRequest purchaseRequest, Department department, Date date,
			String modeOfProcurement, String purpose) {
		purchaseRequest.setDepartment(department);
		purchaseRequest.setPrDate(date);
		purchaseRequest.setModeOfProcurement(modeOfProcurement);
		purchaseRequest.setPurpose(purpose);
		purchaseRequestRepository.save(purchaseRequest);
	}

	@Override
	public void addItems(PurchaseRequest purchaseRequest, List<Item> items) {
		for(Item item : items) {
			PurchaseRequestItem purchaseRequestItem = new PurchaseRequestItem();
			purchaseRequestItem.setItem(item);
			purchaseRequestItem.setQuantity(1);
			purchaseRequestItem.setAbc(0.00);
			purchaseRequestItem.setUnit("piece");
			purchaseRequestItem.setPurchaseRequest(purchaseRequest);
			purchaseRequestItemRepository.save(purchaseRequestItem);
		}	
	}

	@Override
	public void updateItems(List<PurchaseRequestItem> purchaseRequestItem, List<Integer> quantity, List<String> unit, List<Double> abc) {
		
		for (int i = 0; i < purchaseRequestItem.size(); i++) {
			purchaseRequestItem.get(i).setQuantity(quantity.get(i));
			purchaseRequestItem.get(i).setUnit(unit.get(i));
			purchaseRequestItem.get(i).setAbc(abc.get(i));
			purchaseRequestItemRepository.save(purchaseRequestItem.get(i));
		}
	}

	


}
