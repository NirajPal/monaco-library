package com.lms.dao;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lms.constants.SQLConstants;
import com.lms.dto.BookLoan;
import com.lms.dto.Fine;

@Repository
public class BooksDaoImpl implements BooksDao, SQLConstants{

	@Autowired
	SessionFactory sessionFactory;
	
	

	@Override
	public Integer login(String username, String password) {
		
		
		Session session=sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(LOGIN);
		query.setParameter("username", username.trim());
		query.setParameter("password", password.trim());
		BigInteger result=(BigInteger)query.uniqueResult();
		return result.intValue();
		
	}
	
	
	@Override
	public List<Object[]> getBooks(String searchTerm) {
		
		List<Object[]> bookList=null;
		try{
			searchTerm=searchTerm.trim();
			Session session=sessionFactory.getCurrentSession();
			Query query = session.createSQLQuery(BOOK_SEARCH);
			query.setParameter("searchTerm","%"+searchTerm+"%");
			bookList=query.list();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
        return bookList;
	}


	@Override
	public void checkOutBooks(String isbn,String cardId) {
		
		
		Session session=sessionFactory.getCurrentSession();
		BookLoan bookLoan=new BookLoan();
		//bookLoan.setLoanId("1");
		bookLoan.setIsbn(isbn);
		bookLoan.setCardId(new BigInteger(cardId));
		Timestamp currentTimestamp=getCurrentTimeStamp();
		bookLoan.setDateOut(currentTimestamp);
		Timestamp dueDate=getDueDate(currentTimestamp);
		bookLoan.setDueDate(dueDate);
		session.save(bookLoan);
		
	}
	
	
	
	@Override
	public int getAvailabilityCount(String isbn) {
		
		Session session=sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(AVAILABILITY_STATUS);
		query.setParameter("isbn", isbn);
		int result=(int)query.uniqueResult();
		return result;
		
	}
	
	
	@Override
	public int getIssuedBookCount(String cardId) {
		
		Session session=sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(BOOK_ISSUED_COUNT);
		query.setParameter("cardId", cardId);
		//int result=(int)query.uniqueResult();
		BigInteger resultx=(BigInteger)query.uniqueResult();
		return resultx.intValue();
		
	}
	
	@Override
	public boolean isFinePaid(String cardId){
		
		List<Object[]> fineStatusList=null;
		try{
			Session session=sessionFactory.getCurrentSession();
			Query query = session.createSQLQuery(FINE_STATUS);
			query.setParameter("cardId", cardId);
			fineStatusList=query.list();
			if(fineStatusList!=null){
				for(Object object[]: fineStatusList)
				{
					String fineStatus=String.valueOf(object[1]);
					if(fineStatus.equals("N"))
					{
						return false;
					}	
				}
				return true;
			}
			else
				return false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public int getOverDueStatus(String cardId){
		
		int count=0;
		try{
			cardId=cardId.trim();
			Session session=sessionFactory.getCurrentSession();
			Query query = session.createSQLQuery(OVERDUE_STATUS);
			query.setParameter("cardId", cardId);
			List<Object[]> overDueList=query.list();
			if(overDueList!=null)
			{
				for(Object[] object: overDueList)
				{
					Integer days=Integer.parseInt(String.valueOf(object[1]));
					System.out.println(days+"days over due");
					if(days>0)
					{
						count++;
					}
				}
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		
		return count;
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
	public List<Object[]> getBookLoanDetails(String cardId) {
		
		cardId=cardId.trim();
		Session session=sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(BOOK_LOAN_DETAILS);
		query.setParameter("cardId", cardId);
		List<Object[]> bookLoanList=query.list();
		
		return bookLoanList;
	}


	@Override
	public List<Object[]> getBookLoanDetailsByLoanId(String loanId) {
		
		loanId=loanId.trim();
		Session session=sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(BOOK_LOAN_DETAILS_BY_LOAN_ID);
		query.setParameter("loanId", loanId);
		List<Object[]> bookLoanList=query.list();
		
		return bookLoanList;
	}


	@Override
	public int updateBookLoans(String loanId,String isbn) {
		
		loanId=loanId.trim();
		Session session=sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(CHECKIN_BOOK);
		query.setParameter("loanId", loanId);
		int result=query.executeUpdate();
		
		isbn=isbn.trim();
		Query query2=session.createSQLQuery(UPDATE_AVAILABILITY_1);
		query2.setParameter("isbn", isbn);
		int result2=query2.executeUpdate();
		session.flush();
		
		return result;
	}


	@Override
	public int createFine(String loanId, double fineAmt) {
	
		
		Session session=sessionFactory.getCurrentSession();
		Fine fine=new Fine();
		fine.setLoanId(loanId);
		fine.setFineAmount(new BigDecimal(String.valueOf(fineAmt)));
		fine.setPaidStatus("N");
		session.save(fine);
		
		return 1;
	}


	@Override
	public List<Object[]> getChecKOutBookDetails(String isbnNo) {
		
		List<Object[]> bookList=null;
		try{
			isbnNo=isbnNo.trim();
			Session session=sessionFactory.getCurrentSession();
			Query query = session.createSQLQuery(BOOK_SEARCH_BY_ISBN);
			query.setParameter("isbnNo", isbnNo);
			bookList=query.list();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
        return bookList;
	}


	@Override
	public int updateAvailability(String isbn) {
		
		isbn=isbn.trim();
		Session session=sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(UPDATE_AVAILABILITY);
		query.setParameter("isbn", isbn);
		int result=query.executeUpdate();
		session.flush();
		return result;
		
	}



	
	
	
	
	
}
