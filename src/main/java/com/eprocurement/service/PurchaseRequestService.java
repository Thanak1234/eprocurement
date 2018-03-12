package com.eprocurement.service;

import java.sql.Date;
import java.util.List;

import com.eprocurement.domain.Department;
import com.eprocurement.domain.Item;
import com.eprocurement.domain.PurchaseRequest;
import com.eprocurement.domain.PurchaseRequestItem;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PurchaseRequestService {

	public void createNewPurchaseRequest(PurchaseRequest purchaseRequest);

	public void updatePurchaseRequest(PurchaseRequest purchaseRequest, Department department, Date date,
			String modeOfProcurement, String purpose);

	public void addItems(PurchaseRequest purchaseRequest, List<Item> items);

	public void updateItems(List <PurchaseRequestItem> purchaseRequestItem,List <Integer> quantity,List <String> unit);

}
