package com.sahay.expensemanagementapi.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobleExceptionHandler {
	@ExceptionHandler(ExpenseNotFoundException.class)
	public ResponseEntity<Map<String, Object>> expenseNotFound(ExpenseNotFoundException e) {
		Map<String, Object> map = new HashMap<>();
		map.put("message", e.getMessage());
		map.put("Status code", HttpStatus.NOT_FOUND);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EmailAllreadyExistException.class)
	public ResponseEntity<Map<String, Object>> emailAllreadyexists(EmailAllreadyExistException e) {
		Map<String, Object> map = new HashMap<>();
		map.put("message", e.getMessage());
		map.put("Status code", HttpStatus.CONFLICT);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.CONFLICT);
	}
}
