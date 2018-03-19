package com.eprocurement.quotation;

import com.eprocurement.purchaserequest.PurchaseRequestItem;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class QuotationItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Quotation quotation;
	
	@ManyToOne
	private PurchaseRequestItem purchaseRequestItem;
		
	private Double price;

	@LastModifiedBy
	@JsonIgnore
	private String lastModifiedBy;

	@LastModifiedDate
	@JsonIgnore
	private Date lastModifiedDate;

	/**
	 * @return the lastModifiedBy
	 */
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	/**
	 * @param lastModifiedBy the lastModifiedBy to set
	 */
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	/**
	 * @return the lastModifiedDate
	 */
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	/**
	 * @param lastModifiedDate the lastModifiedDate to set
	 */
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

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
