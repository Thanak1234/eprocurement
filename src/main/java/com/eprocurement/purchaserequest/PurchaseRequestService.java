package com.eprocurement.purchaserequest;

import java.sql.Date;
import java.util.List;

import com.eprocurement.department.Department;
import com.eprocurement.item.Item;

public interface PurchaseRequestService {

	public void createNewPurchaseRequest(PurchaseRequest purchaseRequest);

	public void updatePurchaseRequest(PurchaseRequest purchaseRequest, Department department, Date date,
			String modeOfProcurement, String purpose);

	public void addItems(PurchaseRequest purchaseRequest, List<Item> items);

	public void updateItems(List <PurchaseRequestItem> purchaseRequestItem,List <Integer> quantity,List <String> unit, List<Double> abc);
}
