package com.eprocurement.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eprocurement.domain.DepartmentRepository;
import com.eprocurement.domain.PurchaseRequest;
import com.eprocurement.service.PurchaseRequestServiceImpl;

@Controller
@RequestMapping("/pr")
public class PurchaseRequestController {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private PurchaseRequestServiceImpl purchaseRequestService;

	@GetMapping("/new")
	public String getNewPurchaseRequestForm(Model model) {
		model.addAttribute("departments", departmentRepository.findAll());
		model.addAttribute("purchaseRequest", new PurchaseRequest());
		return "purhcaseRequest";
	}
	
	@PostMapping("/save")
	public String savePurchaseRequest(@ModelAttribute PurchaseRequest purchaseRequest) {
		purchaseRequestService.createNewPurchaseRequest(purchaseRequest);
		return "redirect:/pr/"+purchaseRequest.getPrNo()+"/details";	
	}
	
	//display purchase requests
	@GetMapping("/all")
	public String showAllPurchaseRequests(Model model) {
		model.addAttribute("departments", departmentRepository.findAll());
		return "purchaseRequests";
	}
	
	@GetMapping
	public String showDefaultPurchaseRequestManagementPage() {
		return "redirect:/pr/all";
	}
	
	@GetMapping("/{pr}/details")
	public String selectPRItems(@PathVariable PurchaseRequest pr, Model model) {
		model.addAttribute("departments", departmentRepository.findAll());
		return "prDetails";
	}
	
}
