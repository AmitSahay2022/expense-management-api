package com.sahay.expensemanagementapi.service;

import com.sahay.expensemanagementapi.entity.User;

public interface UserService {
	User saveUser(User user);

	String deleteUser();

	User updateUser(User user);

	User findById();
	
	User getLoggedInUser();
}
