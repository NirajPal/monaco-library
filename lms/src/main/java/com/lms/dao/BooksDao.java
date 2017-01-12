package com.lms.dao;

import java.util.List;

import com.lms.dto.Book;

public interface BooksDao {
	
	public Integer login(String username, String password);
	
	public List<Object[]> getBooks(String searchTerm);
	
	public void checkOutBooks(String isbn, String cardId);

	public int getAvailabilityCount(String isbn);

	public int getIssuedBookCount(String cardId);

	public boolean isFinePaid(String cardId);

	public int getOverDueStatus(String cardId);

	public List<Object[]> getBookLoanDetails(String cardId);

	public List<Object[]> getBookLoanDetailsByLoanId(String loanId);

	public int updateBookLoans(String loanId,String isbn);

	public int createFine(String loanId, double fineAmt);

	public List<Object[]> getChecKOutBookDetails(String isbnNo);

	public int updateAvailability(String isbn);

	
	

}
