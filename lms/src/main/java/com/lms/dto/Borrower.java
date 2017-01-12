package com.lms.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="borrowers")
@NamedQuery(name="Borrower.findAll", query="SELECT b FROM Borrower b")
public class Borrower implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CARD_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String cardId;

	@Column(name="ADDRESS")
	private String address;

	@Column(name="CITY")
	private String city;

	@Column(name="COUNTRY")
	private String country;

	@Column(name="COUNTRY_CODE")
	private String countryCode;

	@Column(name="EMAIL")
	private String email;

	@Column(name="FIRST_NAME")
	private String name;

	@Column(name="LAST_NAME")
	private String lastName;

	@Column(name="PHONE_NO")
	private String telephoneNo;

	@Column(name="SSN")
	private String ssn;

	@Column(name="STATE")
	private String state;

	
	public Borrower() {
	}


	public String getCardId() {
		return cardId;
	}


	public void setCardId(String cardId) {
		this.cardId = cardId;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getCountryCode() {
		return countryCode;
	}


	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getTelephoneNo() {
		return telephoneNo;
	}


	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}


	public String getSsn() {
		return ssn;
	}


	public void setSsn(String ssn) {
		this.ssn = ssn;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}

	
}