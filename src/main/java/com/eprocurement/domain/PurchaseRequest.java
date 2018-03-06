package com.eprocurement.domain;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class PurchaseRequest {

	@Id
	private String prNo;
	
	private Date prDate;
	
	@OneToOne
	private Department department;
	
	private String modeOfProcurement;
	
	private String purpose;
	
	public String getPrNo() {
		return prNo;
	}

	public void setPrNo(String prNo) {
		this.prNo = prNo;
	}

	public Date getPrDate() {
		return prDate;
	}

	public void setPrDate(Date prDate) {
		this.prDate = prDate;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getModeOfProcurement() {
		return modeOfProcurement;
	}

	public void setModeOfProcurement(String modeOfProcurement) {
		this.modeOfProcurement = modeOfProcurement;
	}	
}
