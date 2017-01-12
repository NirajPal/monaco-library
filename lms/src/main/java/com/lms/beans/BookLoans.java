package com.lms.beans;

public class BookLoans {
	
	
	private BookDetails book;
	private String dueDate;
	private String dateOut;
	private String dateIn;
	private String cardId;
	private String fineAmount;
	private String extraDays;
	private String loanId;
	
	public String getLoanId() {
		return loanId;
	}
	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}
	public String getExtraDays() {
		return extraDays;
	}
	public void setExtraDays(String extraDays) {
		this.extraDays = extraDays;
	}
	public BookDetails getBook() {
		return book;
	}
	public void setBook(BookDetails book) {
		this.book = book;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getDateOut() {
		return dateOut;
	}
	public void setDateOut(String dateOut) {
		this.dateOut = dateOut;
	}
	public String getDateIn() {
		return dateIn;
	}
	public void setDateIn(String dateIn) {
		this.dateIn = dateIn;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getFineAmount() {
		return fineAmount;
	}
	public void setFineAmount(String fineAmount) {
		this.fineAmount = fineAmount;
	}
	

}
