package com.eprocurement.purchaseorder;

import com.eprocurement.quotation.Quotation;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class PurchaseOrder {

	@Id
	@Column(unique=true)
	private String poNo;
	
	private Date date;
	
	@OneToOne
	private Quotation quotation;

	@LastModifiedBy
	@JsonIgnore
	private String lastModifiedBy;

	@LastModifiedDate
	@JsonIgnore
	private java.util.Date lastModifiedDate;

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
	public java.util.Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	/**
	 * @param lastModifiedDate the lastModifiedDate to set
	 */
	public void setLastModifiedDate(java.util.Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getPoNo() {
		return poNo;
	}
	
	public void setPoNo(String poNo) {
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
