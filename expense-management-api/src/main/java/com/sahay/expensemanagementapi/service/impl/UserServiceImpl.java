package com.sahay.expensemanagementapi.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sahay.expensemanagementapi.entity.User;
import com.sahay.expensemanagementapi.exception.EmailAllreadyExistException;
import com.sahay.expensemanagementapi.exception.UserNotFoundException;
import com.sahay.expensemanagementapi.repository.UserRepository;
import com.sahay.expensemanagementapi.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		if(userRepository.existsByEmail(user.getEmail())) {
			throw new EmailAllreadyExistException("Email allready registered");
		}
		//Before saving the userPassword it should be encrypted
		String encodedPassword=passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		return userRepository.save(user);
	}

	@Override
	public String deleteUser() {
		// TODO Auto-generated method stub
		User user = findById();
		
		userRepository.delete(user);
		return "User Deleted Successfully";
	}

	@Override
	public User updateUser(User usr) {
		// TODO Auto-generated method stub
		User user = findById();
		user.setAge(usr.getAge());
		user.setEmail(usr.getEmail());
		user.setName(usr.getName());
		
		String encodedPassword=passwordEncoder.encode(usr.getPassword());
		user.setPassword(encodedPassword);
		
		return userRepository.save(user);
	}

	@Override
	public User findById() {
		// TODO Auto-generated method stub
		long id = getLoggedInUser().getId();
		return userRepository.findById(id).orElseThrow(()->new UserNotFoundException("user not found with id: "+id));
	}
	
	@Override
	public User getLoggedInUser() {
		// To return the logged in user object 
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		
		User user = userRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException("No User Found with email: "+email));
		return user;
	}

}
