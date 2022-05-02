package com.ws.CRUDtest.controller;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ws.CRUDtest.service.CsvService;

@Controller
@CrossOrigin(origins="*",allowedHeaders="*")
@RequestMapping("/files")
public class FileController {
	@Autowired
	CsvService csvService;
	//export all db to CSV
		@PostMapping("/export")
		public ResponseEntity<Resource> exportCsv() {
			String filename = "allBooks.csv";
			ByteArrayInputStream export = csvService.load();
			InputStreamResource file = new InputStreamResource(export);
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename="+filename)
					.contentType(MediaType.parseMediaType("application/csv"))
					.body(file);
		}
}
