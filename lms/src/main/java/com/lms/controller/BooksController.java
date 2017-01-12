package com.lms.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.beans.Author;
import com.lms.beans.BookDetails;
import com.lms.beans.BookLoans;
import com.lms.beans.BorrowerDetails;
import com.lms.beans.CardDetails;
import com.lms.beans.CheckOutDetails;
import com.lms.beans.Login;
import com.lms.service.BooksService;

@Controller
public class BooksController {
	
	@Autowired
	BooksService booksService;
	
	
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String login(Model model)
	{
		System.out.println("home");
		model.addAttribute("login", new Login());
		return "login";
	}
	
	
	@RequestMapping(value="/signin",method = RequestMethod.POST)
	public String signin(@ModelAttribute Login login, Model model)
	{
		
		
		boolean result=booksService.login(login.getUsername(),login.getPassword());
		if(result==true){
			System.out.println("here"+login.getPassword());
			return	"redirect:/home";
		}
		else
		{
			model.addAttribute("msg", "Username and Password doesnt match");
			return "login";
		}
		
		
	}
	
	
	@RequestMapping(value="/home",method = RequestMethod.GET)
	public String search1(Model model)
	{
		System.out.println("home");
		model.addAttribute("books", new BookDetails());
		return "index";
	}
	
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String search2(Model model)
	{
		System.out.println("/");
		model.addAttribute("books", new BookDetails());
		return "index";
	}
	
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public @ResponseBody String search(@RequestBody BookDetails books)
	{
		
		Map<String, BookDetails> bookDetailsMap=booksService.getBooks(books.getSearchTerm());
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			System.out.println(objectMapper.writeValueAsString(bookDetailsMap));
			return objectMapper.writeValueAsString(bookDetailsMap);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/checkoutbook",method=RequestMethod.GET)
	public String getCheckOutBookDetails(@RequestParam String isbn,Model model)
	{
		BookDetails bookdetails=booksService.getCheckOutBook(isbn);
		
		List<Author> authorList=bookdetails.getAuthors();
		String authors="";
		for (Author a: authorList)
		{
			authors=authors+a.getAuthorName()+" ;";
		}
		
		model.addAttribute("book",bookdetails);
		model.addAttribute("authors",authors);
		model.addAttribute("checkOutDetails", new CheckOutDetails());
		return "checkout";
	}
	
	@RequestMapping(value="/checkout",method=RequestMethod.POST)
	public String checkOutBooks(@ModelAttribute CheckOutDetails checkOutDetails,Model model)
	{
		String isbn=checkOutDetails.getIsbn();
		String cardId=checkOutDetails.getCardId();
		Map<String, String> bookDetailsMap=booksService.checkOutBooks(isbn, cardId);
		model.addAttribute("checkoutdetails",checkOutDetails);
		model.addAttribute("date","date");
		String msg=bookDetailsMap.get("MSG");
		model.addAttribute("msg", msg);
		return "checkoutsuccess";
	}
	
	@RequestMapping(value="/checkin",method=RequestMethod.GET)
	public String checkInBooks(Model model)
	{
		return "checkin";
	}
	
	@RequestMapping(value="/bookloandetails",method=RequestMethod.POST)
	public @ResponseBody String checkInBooks(@RequestBody CardDetails cardDetails) throws JsonProcessingException
	{
		Map<String,BookLoans> bookLoans=booksService.getBookLoanDetails(cardDetails.getCardId());
		ObjectMapper objectMapper=new ObjectMapper();
		String jsonString=objectMapper.writeValueAsString(bookLoans);
		return jsonString;
	}
	
	@RequestMapping(value="/getBook",method=RequestMethod.GET)
	public String checkInBooksByLoanId(@RequestParam String loanId, Model model) throws JsonProcessingException
	{
		String isbn="";
		BookLoans bookLoans=booksService.getBookLoanDetailsByLoanId(loanId,isbn);
		bookLoans.setLoanId(loanId);
		model.addAttribute("bookLoans",bookLoans);
		return "bookloandetail";
	}
	
	@RequestMapping(value="/checkinbook",method=RequestMethod.POST)
	public String checkIn(@ModelAttribute BookLoans bookLoans,Model model) throws JsonProcessingException
	{
		String msg=booksService.checkInBook(bookLoans.getLoanId(), bookLoans.getExtraDays(),bookLoans.getBook().getIsbn());
		model.addAttribute("msg", msg);
		return "checkinsuccess";
	}
}
