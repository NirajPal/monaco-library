package com.lms.constants;

public interface SQLConstants {
	
	
	public final String LOGIN="SELECT COUNT(USER_NAME) FROM USER_DETAILS WHERE USER_NAME= :username AND PASSWORD= :password ;";
	
	public final String BOOK_SEARCH="SELECT B.ISBN, B.TITLE, A.NAME, A.AUTHOR_ID, B.PUBLISHER,B.COVER_IMAGE,B.PAGES,B.AVAILABLE_COPIES,B.TOTAL_COPIES FROM BOOK B, BOOK_AUTHORS BA, AUTHORS A WHERE BA.AUTHOR_ID=A.AUTHOR_ID AND B.ISBN=BA.ISBN AND (B.ISBN LIKE :searchTerm OR A.NAME LIKE :searchTerm OR B.TITLE LIKE :searchTerm);";

	public final String AVAILABILITY_STATUS="SELECT AVAILABLE_COPIES FROM BOOK WHERE ISBN= :isbn ;";
	
	public final String BOOK_ISSUED_COUNT="SELECT COUNT(LOAN_ID) FROM BOOK_LOANS BL WHERE BL.CARD_ID = :cardId and DATE_IN IS NULL;";
	
	public final String FINE_STATUS="SELECT FINE_AMOUNT,PAID_STATUS FROM FINES F, BOOK_LOANS BL WHERE F.LOAN_ID=BL.LOAN_ID AND BL.CARD_ID= :cardId";
	
	public final String OVERDUE_STATUS="SELECT loan_id, DATEDIFF(CURRENT_TIMESTAMP, DUE_DATE) as DAYS from book_loans where CARD_ID= :cardId and DATE_IN is NULL;";

	public final String BOOK_LOAN_DETAILS="SELECT B.TITLE, B.ISBN, B.COVER_IMAGE, BL.LOAN_ID, DATE(BL.DATE_OUT), DATE(BL.DUE_DATE),DATEDIFF( BL.DUE_DATE, BL.DATE_OUT) AS DAYS,B.PUBLISHER FROM BOOK B, BOOK_LOANS BL WHERE BL.ISBN=B.ISBN AND BL.DATE_IN IS NULL AND BL.CARD_ID= :cardId ;";
	
	public final String CHECKIN_BOOK="UPDATE BOOK_LOANS SET DATE_IN =CURRENT_TIMESTAMP WHERE LOAN_ID= :loanId AND DATE_IN IS NULL ;";
	
	public final String CHECK_SSN="SELECT COUNT(SSN) FROM borrowers where SSN= :ssn";
	
	public final String BOOK_LOAN_DETAILS_BY_LOAN_ID="SELECT B.TITLE, B.ISBN, B.COVER_IMAGE, BL.LOAN_ID, DATE(BL.DATE_OUT),DATE(BL.DUE_DATE),DATEDIFF( CURRENT_TIMESTAMP, BL.DUE_DATE) AS EXTRADAYS FROM BOOK B, BOOK_LOANS BL WHERE BL.ISBN=B.ISBN AND BL.DATE_IN IS NULL AND BL.LOAN_ID= :loanId";;
	
	public final String GET_FINE_DETAILS="SELECT F.LOAN_ID , F.FINE_AMOUNT, F.PAID_STATUS FROM FINES F, BOOK_LOANS BL WHERE BL.LOAN_ID=F.LOAN_ID AND BL.CARD_ID= :cardId ;";
	
	public final String PAY_FINE="UPDATE FINES SET PAID_STATUS='Y' WHERE LOAN_ID= :loanId  ;";
	
	public final String BOOK_SEARCH_BY_ISBN="SELECT B.ISBN, B.TITLE, A.NAME, A.AUTHOR_ID, B.PUBLISHER,B.COVER_IMAGE,B.PAGES, B.AVAILABLE_COPIES, B.TOTAL_COPIES FROM BOOK B, BOOK_AUTHORS BA, AUTHORS A WHERE BA.AUTHOR_ID=A.AUTHOR_ID AND B.ISBN=BA.ISBN AND B.ISBN = :isbnNo ; ";
			
	public final String UPDATE_AVAILABILITY="UPDATE BOOK SET AVAILABLE_COPIES = 0 WHERE ISBN= :isbn ;";
	
	public final String UPDATE_AVAILABILITY_1="UPDATE BOOK SET AVAILABLE_COPIES = 1 WHERE ISBN= :isbn ;";
	
	
}