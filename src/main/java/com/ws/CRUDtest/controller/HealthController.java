package com.ws.CRUDtest.controller;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
	@GetMapping("/health")
	public HttpStatus health(){
		return HttpStatus.OK;
	}
}