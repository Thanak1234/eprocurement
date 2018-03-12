package com.eprocurement.purchaseorder;

import com.eprocurement.quotation.QuotationItem;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
//TODO add auditing
@Entity
public class PurchaseOrderItem {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private PurchaseOrder purchaseOrder;
	
	@OneToOne
	private QuotationItem quotationItem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public QuotationItem getQuotationItem() {
		return quotationItem;
	}

	public void setQuotationItem(QuotationItem quotationItem) {
		this.quotationItem = quotationItem;
	}
	
	
}
