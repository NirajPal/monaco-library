package com.lms.service;

import java.math.BigInteger;
import java.util.List;

import com.lms.beans.BorrowerDetails;
import com.lms.beans.FineDetails;

public interface BorrowerService {
	
	
	public Integer createCard(BorrowerDetails borrower);

	public List<FineDetails> getFineDetails(String cardId);

	public String payFine(String loanId);
	
	

}
