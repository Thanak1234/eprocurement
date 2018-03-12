package com.eprocurement.purchaserequest;

import com.eprocurement.department.Department;
import java.sql.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PurchaseRequestRepository extends PagingAndSortingRepository<PurchaseRequest, String> {
	
	Page<PurchaseRequest> findByPrNo(String pr, Pageable pageable);
	Page<PurchaseRequest> findByPrDateBetween(Date startDate,Date endDate, Pageable pageable);
	Page<PurchaseRequest> findByDepartmentAndPrDateBetween(Department department, Date startDate, Date endDate, Pageable pageable);
	
}
