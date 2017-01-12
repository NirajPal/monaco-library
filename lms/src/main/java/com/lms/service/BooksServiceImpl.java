package com.lms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.beans.Author;
import com.lms.beans.BookDetails;
import com.lms.beans.BookLoans;
import com.lms.dao.BooksDao;

@Service
@Transactional
public class BooksServiceImpl implements BooksService{

	
	@Autowired
	BooksDao booksDao;
	
	@Override
	public Map<String,BookDetails> getBooks(String searchTerm) {
		
		List<Object[]> bookList=booksDao.getBooks(searchTerm);
		Map<String,BookDetails> bookDetailsMap=new HashMap<String,BookDetails>();
		
		
		for(Object[] object : bookList)
		{
			String isbn=String.valueOf(object[0]);
			//B.ISBN, B.TITLE, A.NAME,A.AUTHOR_ID, B.PUBLISHER,B.COVER_IMAGE,B.PAGES,B.AVAILABLE_COPIES,B.TOTAL_COPIES
			if(!bookDetailsMap.containsKey(isbn))
			{
				BookDetails bookDetails=new BookDetails();
				bookDetails.setTitle(String.valueOf(object[1]));
				bookDetails.setCoverImage(String.valueOf(object[5]));
				bookDetails.setPages(Integer.parseInt(String.valueOf(object[6])));
				bookDetails.setAvailableCopies(Integer.parseInt(String.valueOf(object[7])));
				bookDetails.setIsbn(String.valueOf(object[0]));
				bookDetails.setPublisher(String.valueOf(object[4]));
				Author author=new Author();
				author.setAuthorId(String.valueOf(object[3]));
				author.setAuthorName(String.valueOf(object[2]));
				List<Author> authorList=new ArrayList<Author>();
				authorList.add(author);
				bookDetails.setAuthors(authorList);
				bookDetailsMap.put(isbn, bookDetails);
			}
			else
			{
				BookDetails tempBook=bookDetailsMap.get(isbn);
				Author author=new Author();
				author.setAuthorId(String.valueOf(object[3]));
				author.setAuthorName(String.valueOf(object[2]));
				List<Author> authorList=tempBook.getAuthors();
				authorList.add(author);
			}
		}
		
		return bookDetailsMap;
	}

	@Override
	public Map<String,String> checkOutBooks(String isbn,String cardId) {
		
		Map<String,String> resultMap=new HashMap<String,String>();
		int availableCopies=booksDao.getAvailabilityCount(isbn);
		System.out.println(availableCopies);
		if(availableCopies>0)
		{
			int issuedCount=booksDao.getIssuedBookCount(cardId);
			if(issuedCount<3){
				
				System.out.println(issuedCount);
				int overDueStatus=booksDao.getOverDueStatus(cardId);
				boolean fineStatus=booksDao.isFinePaid(cardId);
				if(overDueStatus>0)
				{
					System.out.println("ERR03");
					resultMap.put("MSG","Unable to check out.There is a book overdue.");
					
				}else if(fineStatus==false)
				{
					System.out.println("ERR03");
					resultMap.put("MSG","Unable to check out.Fine not paid.");
					
				}
				else{
					System.out.println("SUCCESS03");
					booksDao.checkOutBooks(isbn, cardId);
					int updateResult=booksDao.updateAvailability(isbn);
					resultMap.put("MSG", "Book checked out successfully");
				}
			}
			else
			{
				//show error that cannot issue books
				resultMap.put("MSG", "Maximum 3 books can be issued");	
			}	
		}
		else
		{
			resultMap.put("MSG", "Book not available");
			//show error that availableCopies not possible
		}
		
		return resultMap;
	}

	@Override
	public Map<String, BookLoans> getBookLoanDetails(String cardId) {
		
		Map<String,BookLoans> resultMap=new HashMap<String,BookLoans>();
		List<Object[]> bookLoanList=booksDao.getBookLoanDetails(cardId);
		
		for(Object object[] : bookLoanList)
		{
			String loanId=String.valueOf(object[3]);
			BookLoans bookLoan=new BookLoans();
			bookLoan.setLoanId(loanId);
			BookDetails bookDetail=new BookDetails();
			bookDetail.setTitle(String.valueOf(object[0]));
			bookDetail.setCoverImage(String.valueOf(object[2]));
			bookDetail.setPublisher(String.valueOf(object[7]));
			bookDetail.setIsbn(String.valueOf(object[1]));
			bookLoan.setBook(bookDetail);
			bookLoan.setDateOut(String.valueOf(object[4]));
			bookLoan.setDueDate(String.valueOf(object[5]));
			bookLoan.setCardId(cardId);
			resultMap.put(loanId,bookLoan);
		}
		return resultMap;
	}

	@Override
	public BookLoans getBookLoanDetailsByLoanId(String loanId,String isbn) {
		
		List<Object[]> bookDetailsList = booksDao.getBookLoanDetailsByLoanId(loanId);
		BookLoans bookLoan=new BookLoans();
		for(Object[] object: bookDetailsList)
		{
			BookDetails book=new BookDetails();
			book.setIsbn(String.valueOf(object[1]));
			book.setCoverImage(String.valueOf(object[2]));
			book.setTitle(String.valueOf(object[0]));
			bookLoan.setBook(book);
			
			bookLoan.setDateOut(String.valueOf(object[4]));
			bookLoan.setDueDate(String.valueOf(object[5]));
			String extraDays=String.valueOf(object[6]);
			bookLoan.setExtraDays(extraDays);
			int extraDaysInt=Integer.parseInt(extraDays);
			if(extraDaysInt>0){
					double fineAmt=extraDaysInt*0.25; 
					bookLoan.setFineAmount(String.valueOf(fineAmt));
			}
			
		}
		
		return bookLoan;
	}

	@Override
	public String checkInBook(String loanId, String extraDays,String isbn) {
		
		int result=booksDao.updateBookLoans(loanId,isbn);
		String message="";
		int extraDaysInt=Integer.parseInt(extraDays);
		if(extraDaysInt>0){
				double fineAmt=extraDaysInt*0.25; 
				result=booksDao.createFine(loanId,fineAmt);
		}
		if(result>0)
			message="Book Checked In Successfully";
		else
			message="There was error in check in process.Please Try again Later";
		
		return message;
	}

	@Override
	public BookDetails getCheckOutBook(String isbnNo) {
		
		List<Object[]> bookList=booksDao.getChecKOutBookDetails(isbnNo);
		Map<String,BookDetails> bookDetailsMap=new HashMap<String,BookDetails>();
		
		for(Object[] object : bookList)
		{
			String isbn=String.valueOf(object[0]);
			
			if(!bookDetailsMap.containsKey(isbn))
			{
				BookDetails bookDetails=new BookDetails();
				bookDetails.setTitle(String.valueOf(object[1]));
				bookDetails.setCoverImage(String.valueOf(object[5]));
				bookDetails.setPages(Integer.parseInt(String.valueOf(object[6])));
				bookDetails.setAvailableCopies(Integer.parseInt(String.valueOf(object[7])));
				bookDetails.setIsbn(String.valueOf(object[0]));
				bookDetails.setPublisher(String.valueOf(object[4]));
				Author author=new Author();
				author.setAuthorId(String.valueOf(object[3]));
				author.setAuthorName(String.valueOf(object[2]));
				List<Author> authorList=new ArrayList<Author>();
				authorList.add(author);
				bookDetails.setAuthors(authorList);
				bookDetailsMap.put(isbn, bookDetails);
			}
			else
			{
				BookDetails tempBook=bookDetailsMap.get(isbn);
				Author author=new Author();
				author.setAuthorId(String.valueOf(object[3]));
				author.setAuthorName(String.valueOf(object[2]));
				List<Author> authorList=tempBook.getAuthors();
				authorList.add(author);
			}
		}
		
		return bookDetailsMap.get(isbnNo);
	}

	@Override
	public boolean login(String username, String password) {
		
		Integer count=booksDao.login(username,password);
		if(count==1)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	
	}
	
	
}
