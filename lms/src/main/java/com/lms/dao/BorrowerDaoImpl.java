package com.lms.dao;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import com.lms.beans.BorrowerDetails;
import com.lms.constants.SQLConstants;
import com.lms.dto.Borrower;

@Repository
public class BorrowerDaoImpl implements BorrowerDao, SQLConstants{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Integer createCard(BorrowerDetails borrowerDetails) {
		
		try{
			Session session=sessionFactory.getCurrentSession();
			Borrower borrower=new Borrower();
			BeanUtils.copyProperties(borrowerDetails, borrower);
			session.persist(borrower);
			session.flush();
			String cardId=borrower.getCardId();
			
			return Integer.parseInt(cardId);
		}
		catch (DataIntegrityViolationException e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public Timestamp getCurrentTimeStamp()
	{
		Date date= new Date();
		Timestamp currentTimestamp=new Timestamp(date.getTime());
		return currentTimestamp;
	}
	
	public Timestamp getDueDate(Timestamp currentTime){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentTime);
		calendar.add(Calendar.DAY_OF_WEEK, 14);
		Timestamp dueDate=new Timestamp(calendar.getTime().getTime());
		return dueDate;
	}

	@Override
	public List<Object[]> getFineDetails(String cardId) {
		
		Session session=sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(GET_FINE_DETAILS);
		query.setParameter("cardId", cardId);
		List<Object[]> fineList=query.list();
		return fineList;
	}

	@Override
	public int payFine(String loanId) {
		
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createSQLQuery(PAY_FINE);
		query.setParameter("loanId", loanId);
		int result=query.executeUpdate();
		return result;
	}

	@Override
	public boolean checkDuplicateSSN(String ssn) {

		Session session=sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(CHECK_SSN);
		query.setParameter("ssn", ssn);
		BigInteger result=(BigInteger) query.uniqueResult();
		Integer x=Integer.parseInt(result.toString());
		if(x>0)
			return true;
		else 
			return false;
		
	}

	
}
