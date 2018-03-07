package com.eprocurement.service;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.eprocurement.domain.Department;
import com.eprocurement.domain.Item;
import com.eprocurement.domain.PurchaseRequest;
import com.eprocurement.domain.PurchaseRequestItem;

public interface PurchaseRequestService {

	public void createNewPurchaseRequest(PurchaseRequest purchaseRequest);
	public void updatePurchaseRequest(PurchaseRequest purchaseRequest, Department department, Date date, String modeOfProcurement, String purpose);
	public void addItems(PurchaseRequest purchaseRequest, List<Item> items);
	public void updateItems(PurchaseRequestItem purchaseRequestItem, int quantity, String unit);
}
