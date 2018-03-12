package com.eprocurement.purchaseorder;

import com.eprocurement.quotation.Quotation;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
//TODO add auditing
@Entity
public class PurchaseOrder {

	@Id
	@GeneratedValue
	private Long poNo;
	
	private Date date;
	
	@OneToOne
	private Quotation quotation;
	
	public Long getPoNo() {
		return poNo;
	}

	public void setPoNo(Long poNo) {
		this.poNo = poNo;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Quotation getQuotation() {
		return quotation;
	}

	public void setQuotation(Quotation quotation) {
		this.quotation = quotation;
	}
	
}
