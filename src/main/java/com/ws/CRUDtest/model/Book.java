package com.ws.CRUDtest.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "books")
@Data
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="isbn")
	private String isbn;
	@Column(name="author_name")
	private String authorName;
	@Column(name="author_surname")
	private String authorSurname;
	@Column(name="book_name",nullable = false)
	private String bookName;
	@Column(name="description")
	private String description;
}
