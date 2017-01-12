package com.lms.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the fines database table.
 * 
 */
@Entity
@Table(name="fines")
@NamedQuery(name="Fine.findAll", query="SELECT f FROM Fine f")
public class Fine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="LOAN_ID")
	private String loanId;

	@Column(name="FINE_AMOUNT")
	private BigDecimal fineAmount;

	@Column(name="PAID_STATUS")
	private String paidStatus;


	public Fine() {
	}

	public String getLoanId() {
		return this.loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public BigDecimal getFineAmount() {
		return this.fineAmount;
	}

	public void setFineAmount(BigDecimal fineAmount) {
		this.fineAmount = fineAmount;
	}

	public String getPaidStatus() {
		return this.paidStatus;
	}

	public void setPaidStatus(String paidStatus) {
		this.paidStatus = paidStatus;
	}


}