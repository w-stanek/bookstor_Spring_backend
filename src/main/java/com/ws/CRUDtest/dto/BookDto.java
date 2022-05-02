package com.ws.CRUDtest.dto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ws.CRUDtest.model.Book;
@Repository
public interface BookDto extends JpaRepository<Book, Integer> {

	@Query("SELECT b FROM Book b WHERE b.bookName LIKE %?1%"
			+"OR b.authorName LIKE %?1%"
			+"OR b.authorSurname LIKE %?1%"
			+"OR CONCAT(b.authorName,' ',b.authorSurname) LIKE %?1%"
			+"OR b.bookName LIKE %?1%"
			+"OR b.isbn LIKE %?1%")
	
	List<Book> findAllByKeyword(String keyword);
}
