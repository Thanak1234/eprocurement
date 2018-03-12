package com.eprocurement.purchaserequest;
import java.util.List;

import com.eprocurement.item.Item;
import com.eprocurement.user.User;
import com.eprocurement.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pritems")
public class PurchaseRequestItemsRestController {
	
	@Autowired
	private PurchaseRequestItemsRepository purchaseRequestItemRepository;
	
	@Autowired
	private PurchaseRequestService purchaseRequestService;
	
	@Autowired
	private UserRepository userRepository;
	
	//TODO move this to service layer
	@GetMapping("/{pr}")
	public Page<PurchaseRequestItem> getPRItems(Pageable pageable,
			@PathVariable PurchaseRequest pr, Authentication authentication){	
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		if(userDetails.getAuthorities().toString().equals("[ROLE_ADMIN]")) {
			return purchaseRequestItemRepository.findByPurchaseRequest(pr, pageable);
		}else {
			User user= userRepository.findByUsername(userDetails.getUsername());
			if(pr.getDepartment() == user.getDepartment()) {
				return purchaseRequestItemRepository.findByPurchaseRequest(pr, pageable);
			}else {
				throw new AccessDeniedException("403");
			}
		}		
	}
	
	
	@PostMapping("/save")
	public void savePrItems(@RequestParam List<PurchaseRequestItem> item, 
			@RequestParam List<Integer> quantity, 
			@RequestParam List<String> unit ) {
		purchaseRequestService.updateItems(item, quantity, unit);
		
	}
	
	@PostMapping("/add")
	public void addItems(@RequestParam List<Item> items, @RequestParam PurchaseRequest pr) {
		purchaseRequestService.addItems(pr, items);
	}

	@PostMapping("/delete")
	public void removeItem(@RequestParam PurchaseRequestItem id) {
		purchaseRequestItemRepository.delete(id);
	}
}