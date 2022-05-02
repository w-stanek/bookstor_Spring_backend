package com.ws.CRUDtest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ws.CRUDtest.model.Book;
import com.ws.CRUDtest.service.BookService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class BooksController {
	@Autowired
	private BookService bookservice;

	//insert book

	@PostMapping("/book")
	public ResponseEntity<Book> AddBook(@RequestBody Book book){
		Book savedBook = bookservice.SaveBook(book);
		if(savedBook != null)
		return new ResponseEntity<>(savedBook,HttpStatus.CREATED);
		else return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
	
	//update book
	@PatchMapping("/book")
	public ResponseEntity<Book> UpdateBook(@RequestParam int id,@RequestBody Book book){
		Book updatedBook = book;
		if(updatedBook.getBookName()!="") {
		updatedBook = bookservice.UpdateBook(id,book);
		if(updatedBook != null)
		return new ResponseEntity<Book>(updatedBook,HttpStatus.OK);
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
		else return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
	
	//delete book
	@DeleteMapping("/book")
	public  ResponseEntity<Book> DeleteBook(@RequestParam int id){
		Book deletedBook = bookservice.Delete(id);
		if (deletedBook != null)
		return new ResponseEntity<Book>(deletedBook,HttpStatus.OK);
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	//get all books
	@PostMapping("/books")
	public ResponseEntity<List<Book>> GetAllBooks(){
		List<Book> books =  bookservice.GetAll();
		return new ResponseEntity<List<Book>>(books,HttpStatus.OK);
	}
	
	//find book by keyword
	@PostMapping("/book/findresult")
	public ResponseEntity<List<Book>> FilterBook(@RequestParam String keyword){
		List<Book> books = bookservice.Filter(keyword);
		if(!books.isEmpty())
		return new ResponseEntity<List<Book>>(books,HttpStatus.OK);
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
