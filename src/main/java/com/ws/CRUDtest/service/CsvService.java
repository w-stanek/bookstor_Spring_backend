package com.ws.CRUDtest.service;

import java.io.ByteArrayInputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ws.CRUDtest.dto.BookDto;
import com.ws.CRUDtest.dto.CsvDto;
import com.ws.CRUDtest.model.Book;

@Service
public class CsvService {
	@Autowired
	private CsvDto csvDto;
	@Autowired
	private BookDto bookDto;
	
	public ByteArrayInputStream load() {
		List<Book> books = bookDto.findAll();
		ByteArrayInputStream in = csvDto.downloadCsvFile(books);
	return in;
	}

}
