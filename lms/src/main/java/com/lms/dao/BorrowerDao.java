package com.lms.dao;

import java.math.BigInteger;
import java.util.List;

import com.lms.beans.BorrowerDetails;

public interface BorrowerDao {
	
	
	public Integer createCard(BorrowerDetails borrower);

	public List<Object[]> getFineDetails(String cardId);

	public int payFine(String loanId);

	public boolean checkDuplicateSSN(String ssn);
	

}
