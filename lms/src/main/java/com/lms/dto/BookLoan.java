package com.lms.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the book_loans database table.
 * 
 */
@Entity
@Table(name="book_loans")
@NamedQuery(name="BookLoan.findAll", query="SELECT b FROM BookLoan b")
public class BookLoan implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="LOAN_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String loanId;

	@Column(name="CARD_ID")
	private java.math.BigInteger cardId;

	@Column(name="DATE_IN")
	private Timestamp dateIn;

	@Column(name="DATE_OUT")
	private Timestamp dateOut;

	@Column(name="DUE_DATE")
	private Timestamp dueDate;

	@Column(name="ISBN")
	private String isbn;

	public BookLoan() {
	}

	public String getLoanId() {
		return this.loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public java.math.BigInteger getCardId() {
		return this.cardId;
	}

	public void setCardId(java.math.BigInteger cardId) {
		this.cardId = cardId;
	}

	public Timestamp getDateIn() {
		return this.dateIn;
	}

	public void setDateIn(Timestamp dateIn) {
		this.dateIn = dateIn;
	}

	public Timestamp getDateOut() {
		return this.dateOut;
	}

	public void setDateOut(Timestamp dateOut) {
		this.dateOut = dateOut;
	}

	public Timestamp getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Timestamp dueDate) {
		this.dueDate = dueDate;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

}