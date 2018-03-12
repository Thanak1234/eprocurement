package com.eprocurement.quotation;

import com.eprocurement.purchaserequest.PurchaseRequestItem;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
//TODO add auditing
@Entity
public class QuotationItem {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private Quotation quotation;
	
	@ManyToOne
	private PurchaseRequestItem purchaseRequestItem;
		
	private Double price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Quotation getQuotation() {
		return quotation;
	}

	public void setQuotation(Quotation quotation) {
		this.quotation = quotation;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public PurchaseRequestItem getPurchaseRequestItem() {
		return purchaseRequestItem;
	}

	public void setPurchaseRequestItem(PurchaseRequestItem purchaseRequestItem) {
		this.purchaseRequestItem = purchaseRequestItem;
	}
	
}
