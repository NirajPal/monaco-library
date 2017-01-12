package com.lms.dto;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the book_authors database table.
 * 
 */
@Entity
@Table(name="book_authors")
@NamedQuery(name="BookAuthor.findAll", query="SELECT b FROM BookAuthor b")
public class BookAuthor implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BookAuthorPK id;

	public BookAuthor() {
	}

	public BookAuthorPK getId() {
		return this.id;
	}

	public void setId(BookAuthorPK id) {
		this.id = id;
	}

}