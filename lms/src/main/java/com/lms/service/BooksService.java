package com.lms.service;

import java.util.Map;

import com.lms.beans.BookDetails;
import com.lms.beans.BookLoans;

public interface BooksService {
	
	public boolean login(String username, String password);
	
	public Map<String, BookDetails> getBooks(String searchTerm);

	public Map<String, String> checkOutBooks(String isbn, String cardId);
	
	public Map<String, BookLoans> getBookLoanDetails(String cardId);

	public BookLoans getBookLoanDetailsByLoanId(String loanId,String isbn);
	
	public String checkInBook(String loanId,String extraDays,String isbn);

	public BookDetails getCheckOutBook(String isbn);

	
	

}