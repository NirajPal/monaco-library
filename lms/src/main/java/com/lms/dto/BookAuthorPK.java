package com.lms.dto;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the book_authors database table.
 * 
 */
@Embeddable
public class BookAuthorPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="AUTHOR_ID", insertable=false, updatable=false)
	private String authorId;

	@Column(insertable=false, updatable=false)
	private String isbn;

	public BookAuthorPK() {
	}
	public String getAuthorId() {
		return this.authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	public String getIsbn() {
		return this.isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BookAuthorPK)) {
			return false;
		}
		BookAuthorPK castOther = (BookAuthorPK)other;
		return 
			this.authorId.equals(castOther.authorId)
			&& this.isbn.equals(castOther.isbn);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.authorId.hashCode();
		hash = hash * prime + this.isbn.hashCode();
		
		return hash;
	}
}