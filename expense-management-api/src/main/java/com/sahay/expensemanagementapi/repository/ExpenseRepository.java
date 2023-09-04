package com.sahay.expensemanagementapi.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sahay.expensemanagementapi.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

	Page<Expense> findByUserIdAndCategory(long userId, String category, Pageable page);

	Page<Expense> findByUserIdAndNameContaining(long userId, String key, Pageable page);
	
	Page<Expense> findByUserId(long id,Pageable page);
	
	Optional<Expense> findByUserIdAndId(long userId,long expenseId);
}
