package com.sahay.expensemanagementapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sahay.expensemanagementapi.entity.Expense;

public interface ExpenseService {
	Expense saveExpense(Expense dto);

	Expense updateExpense(Expense dto,long id);

	String deleteExpense(long id);

	Expense getExpenseById(long id);

	Page<Expense> getAllExpenseList(Pageable page);
	
	Page<Expense> findByCategory(String category, Pageable pageable);
	
	Page<Expense> findByNameContaining(String key,Pageable pageable);
}
