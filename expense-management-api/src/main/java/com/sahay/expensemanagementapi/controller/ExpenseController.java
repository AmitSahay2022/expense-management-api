package com.sahay.expensemanagementapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sahay.expensemanagementapi.entity.Expense;
import com.sahay.expensemanagementapi.service.ExpenseService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/expenses")
public class ExpenseController {
	public ExpenseService expenseService;
    @PostMapping
	public ResponseEntity<Expense> saveExpense(@Valid @RequestBody Expense expense){
		return new ResponseEntity<Expense>(expenseService.saveExpense(expense),HttpStatus.CREATED);
	}
    @PutMapping("{id}")
    public ResponseEntity<Expense> updateExpense(@Valid @RequestBody Expense expense, @PathVariable long id){
    	return new ResponseEntity<Expense>(expenseService.updateExpense(expense, id),HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable long id){
    	return new ResponseEntity<String>(expenseService.deleteExpense(id),HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<Expense> getById(@PathVariable long id){
    	return new ResponseEntity<Expense>(expenseService.getExpenseById(id),HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<Page<Expense>> getAllExpense(Pageable pageable){
    	return new ResponseEntity<Page<Expense>>(expenseService.getAllExpenseList(pageable),HttpStatus.OK);
    }
    
    @GetMapping("category")
    public ResponseEntity<Page<Expense>> getAllExpenseByCategory(@RequestParam String category, Pageable pageable){
    	return new ResponseEntity<Page<Expense>>(expenseService.findByCategory(category, pageable),HttpStatus.OK);
    }
    
    @GetMapping("search-by-name")
    public ResponseEntity<Page<Expense>> getAllExpenseByNameContains(@RequestParam String key, Pageable pageable){
    	return new ResponseEntity<Page<Expense>>(expenseService.findByNameContaining(key, pageable),HttpStatus.OK);
    }
    
}











