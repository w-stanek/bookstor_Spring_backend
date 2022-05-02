package com.ws.CRUDtest.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ws.CRUDtest.dto.BookDto;
import com.ws.CRUDtest.model.Book;

@Service
public class BookService {
	@Autowired
	private BookDto bookDto;

	public Book SaveBook(Book book) {
		if(book.getBookName()!="")
		return bookDto.save(book);
		else return null;
	}

	public List<Book> GetAll() {
		return bookDto.findAll();
	}
	//update Book
	public Book UpdateBook(int id, Book book) {
		if(bookDto.existsById(id)) {
			Book updatedBook = book;
			updatedBook.setIsbn(book.getIsbn());
			updatedBook.setAuthorName(book.getAuthorName());
			updatedBook.setAuthorSurname(book.getAuthorSurname());
			updatedBook.setBookName(book.getBookName());
			updatedBook.setDescription(book.getDescription());
			bookDto.save(updatedBook);
			return updatedBook;}
		else return null;

	}
	//delete Book
	public Book Delete(int id) {
		Book deletedBook = bookDto.findById(id).orElse(deletedBook = null);
		if(deletedBook != null)
			bookDto.delete(deletedBook);
		return deletedBook;
	}
	//find by keyword
	public List<Book> Filter(String keyword) {
		List<Book> books = new ArrayList<Book>();
		if (keyword!="")
			books = bookDto.findAllByKeyword(keyword);
		return books;
	}
	//private Book BookExists(int id) {
	//	return bookDto.findById(id).orElseThrow(()-> new RuntimeException("not found"));
	//}
}
