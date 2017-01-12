package com.lms.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.beans.BorrowerDetails;
import com.lms.beans.FineDetails;
import com.lms.dao.BorrowerDao;

@Service
@Transactional
public class BorrowerServiceImpl implements BorrowerService{

	@Autowired
	BorrowerDao borrowerDao;

	@Override
	public Integer createCard(BorrowerDetails borrowerDetails) {
		
		Integer cardId=0;
		String ssn=borrowerDetails.getSsn();
		boolean ssnCheck=borrowerDao.checkDuplicateSSN(ssn);
		if(ssnCheck==true)
		{
			return -1;
		}
		else{
			cardId=borrowerDao.createCard(borrowerDetails);
		}
		
		return cardId;
	}

	@Override
	public List<FineDetails> getFineDetails(String cardId) {
		
		List<Object[]> fineList=borrowerDao.getFineDetails(cardId);
		
		List<FineDetails> fineDetails=new ArrayList<FineDetails>();
		for(Object[] object : fineList)
		{
			FineDetails fine=new FineDetails();
			fine.setLoanid(String.valueOf(object[0]));
			fine.setFineAmount(String.valueOf(object[1]));
			fine.setPaidStatus(String.valueOf(object[2]));
			fineDetails.add(fine);
		}
		return fineDetails;
	}

	@Override
	public String payFine(String loanId) {
		
		int result=borrowerDao.payFine(loanId);
		String msg="";
		if(result>0)
			msg="Fine Paid Successfully for Loan Id"+loanId;
		else
			msg="Error Occured while fine payment";

		return msg;
	}
	
	
}
