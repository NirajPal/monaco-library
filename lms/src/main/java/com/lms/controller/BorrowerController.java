package com.lms.controller;

import java.util.List;

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
import com.lms.beans.BookLoans;
import com.lms.beans.BorrowerDetails;
import com.lms.beans.CardDetails;
import com.lms.beans.FineDetails;
import com.lms.service.BorrowerService;

@Controller
public class BorrowerController {
	
	@Autowired
	BorrowerService borrowerService;
	
	@RequestMapping(value="/borrower",method = RequestMethod.GET)
	public String addBorrower(Model model)
	{
		model.addAttribute("msg","");
		model.addAttribute("borrower", new BorrowerDetails());
		return "addborrower";
	}
	
	@RequestMapping(value="/createcard",method = RequestMethod.POST)
	public String createCard(@ModelAttribute BorrowerDetails borrower,Model model)
	{
		System.out.print(borrower.getSsn());
		int result=borrowerService.createCard(borrower);
		String msg="";
		if(result>0)
			msg="Card successfully created ! Card ID:"+result;
		else if (result==-1){
			msg="Card cannot be created.SSN already present";
			model.addAttribute("msg",msg);
			model.addAttribute("borrower",borrower);
			return "addborrower";
		}
		else
			msg="Error in card creation";
		
		model.addAttribute("msg",msg);
		model.addAttribute("borrower",borrower);
		return "cardsuccess";
	}
	
	@RequestMapping(value="/fines",method = RequestMethod.GET)
	public String getFinesPage()
	{
		return "fines";
	}
	
	@RequestMapping(value="/finedetails",method = RequestMethod.POST)
	public @ResponseBody String getFineDetails(@RequestBody CardDetails cardDetails) throws JsonProcessingException
	{
		List<FineDetails> fineDetails=borrowerService.getFineDetails(cardDetails.getCardId());
		ObjectMapper objectMapper=new ObjectMapper();
		String jsonString=objectMapper.writeValueAsString(fineDetails);
		return jsonString;
	}
	
	@RequestMapping(value="/payfine",method = RequestMethod.GET)
	public String payFine(@RequestParam String id, Model model)
	{
		String msg=borrowerService.payFine(id);
		model.addAttribute("msg", msg);
		return "paymentresult";
	}
	
}