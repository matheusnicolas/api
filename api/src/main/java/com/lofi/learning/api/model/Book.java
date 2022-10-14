package com.lofi.learning.api.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "books")
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 180)
	private String author;

	@Column(name = "launch_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date launchDate;
	
	@Column(nullable = false)
	private Double price;
	
	@Column(nullable = false, length = 250)
	private String title;
	
	public Book() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getLaunchDate() {
		return launchDate;
	}

	public void setLaunchDate(Date launchDate) {
		this.launchDate = launchDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Book)) return false;

		Book book = (Book) o;

		if (getId() != null ? !getId().equals(book.getId()) : book.getId() != null) return false;
		if (getAuthor() != null ? !getAuthor().equals(book.getAuthor()) : book.getAuthor() != null) return false;
		if (getLaunchDate() != null ? !getLaunchDate().equals(book.getLaunchDate()) : book.getLaunchDate() != null)
			return false;
		if (getPrice() != null ? !getPrice().equals(book.getPrice()) : book.getPrice() != null) return false;
		return getTitle() != null ? getTitle().equals(book.getTitle()) : book.getTitle() == null;
	}

	@Override
	public int hashCode() {
		int result = getId() != null ? getId().hashCode() : 0;
		result = 31 * result + (getAuthor() != null ? getAuthor().hashCode() : 0);
		result = 31 * result + (getLaunchDate() != null ? getLaunchDate().hashCode() : 0);
		result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
		result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
		return result;
	}
}