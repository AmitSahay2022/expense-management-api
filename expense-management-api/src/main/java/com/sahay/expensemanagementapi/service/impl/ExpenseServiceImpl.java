package com.sahay.expensemanagementapi.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sahay.expensemanagementapi.entity.Expense;
import com.sahay.expensemanagementapi.entity.User;
import com.sahay.expensemanagementapi.exception.ExpenseNotFoundException;
import com.sahay.expensemanagementapi.repository.ExpenseRepository;
import com.sahay.expensemanagementapi.service.ExpenseService;
import com.sahay.expensemanagementapi.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ExpenseServiceImpl implements ExpenseService{
	private UserService userService; //---->to get user info
	
    private ExpenseRepository expenseRepository;
    
	@Override
	public Expense saveExpense(Expense expense) {
		// Convert ExpenseDto to Expense object
		User loggedInUser = userService.getLoggedInUser();
		expense.setUser(loggedInUser);
		return expenseRepository.save(expense);
	}

	@Override
	public Expense updateExpense(Expense dto, long id) {
		
		Expense expense = getExpenseById(id);
		
		expense.setAmount(dto.getAmount());
		expense.setCategory(dto.getDescription());
		expense.setDate(dto.getDate());
		expense.setName(dto.getName());
		expense.setDescription(dto.getDescription());
		return expenseRepository.save(expense);
		
	}

	@Override
	public String deleteExpense(long id) {
		// TODO Auto-generated method stub
		Expense expenseById = getExpenseById(id);
		
		expenseRepository.delete(expenseById);
		return "Expense Deleted Successfully";
	}

	@Override
	public Expense getExpenseById(long id) {
		// TODO Auto-generated method stub
		long userId = userService.getLoggedInUser().getId();
		return expenseRepository
				.findByUserIdAndId(userId, id)
				.orElseThrow(()->new ExpenseNotFoundException("Expense not found with id: "+id));
		
	}

	@Override
	public Page<Expense> getAllExpenseList(Pageable page) {
		// TODO Auto-generated method stub
		long id = userService.getLoggedInUser().getId();
		Page<Expense> allExpenses = expenseRepository.findByUserId(id, page);
		
		return allExpenses;
	}
	
	@Override
	public Page<Expense> findByCategory(String category, Pageable pageable) {
		// TODO Auto-generated method stub
		long id = userService.getLoggedInUser().getId();
		return expenseRepository.findByUserIdAndCategory(id,category,pageable);
	}
	
	@Override
	public Page<Expense> findByNameContaining(String key, Pageable pageable) {
		// TODO Auto-generated method stub
		long id = userService.getLoggedInUser().getId();
		return expenseRepository.findByUserIdAndNameContaining(id,key, pageable);
	}

}
