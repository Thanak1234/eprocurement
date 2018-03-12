package com.eprocurement.web;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eprocurement.domain.Department;
import com.eprocurement.domain.PurchaseRequest;
import com.eprocurement.domain.PurchaseRequestRepository;
import com.eprocurement.service.PurchaseRequestService;

@RestController
public class PurchaseRequestRestController {
	
	@Autowired
	private PurchaseRequestRepository purchaseRequestRepository;
	
	@Autowired
	private PurchaseRequestService purchaseRequestService;
	
	@GetMapping("/api/pr")
	public Page<PurchaseRequest> getAllPurchaseRequests(Pageable pageable,
			@RequestParam(defaultValue="") String searchBy,
			@RequestParam(required=false) String pr, 
			@RequestParam(required= false)Department department,
			@RequestParam(required= false) Date startDate, @RequestParam(required=false) Date endDate) {
		Page<PurchaseRequest> pagePR;
		
		switch (searchBy) {
		case "prNo":
			pagePR = purchaseRequestRepository.findByPrNo(pr, pageable);
			break;
		case "department":
			pagePR = purchaseRequestRepository.findByDepartmentAndPrDateBetween(department, startDate, endDate, pageable);
			break;
		default:
			pagePR =  purchaseRequestRepository.findByPrDateBetween(startDate, endDate, pageable);		
			break;
		}
		
		return pagePR;
	}
	
	@GetMapping("/api/pr/{department}/all")
	public Page<PurchaseRequest> getPuchaseRequestByDepartment(@PathVariable Department department,
			@RequestParam Date startDate, @RequestParam Date endDate, Pageable pageable){
		return purchaseRequestRepository.findByDepartmentAndPrDateBetween(department, startDate, endDate, pageable);
	}
	
	
	@PostMapping("/api/pr/{pr}")
	public void updatePurchaseRequest(@PathVariable PurchaseRequest pr,
			@RequestParam(value="department.id") Department department, 
			@RequestParam Date prDate, @RequestParam String modeOfProcurement,@RequestParam String purpose) {
		purchaseRequestService.updatePurchaseRequest(pr, department, prDate, modeOfProcurement, purpose);
	}
}
