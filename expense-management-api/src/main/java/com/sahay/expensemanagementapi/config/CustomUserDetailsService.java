package com.sahay.expensemanagementapi.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sahay.expensemanagementapi.entity.User;
import com.sahay.expensemanagementapi.exception.UserNotFoundException;
import com.sahay.expensemanagementapi.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	
	//------UserRepository--is used to read user information from database
	//-------NOTE: user password must be in encrypted format inside the DB
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository
		     .findByEmail(email)
		     .orElseThrow(
		    		 ()->new UserNotFoundException("user not found with email: "+email));
		
		//if user has roles then we can add it into below Set of authorities. HERE we don't have ROLES
		Set<GrantedAuthority> authorities=new HashSet<>(); //so empty set
		
		//------User class provided by Spring security takes 3 parameter
		// (1) userName (2) userPassword (3) Collection of GrantedAuthority
		
		return new org.springframework.security.core.userdetails.User(
				user.getEmail(),
				user.getPassword(),
				authorities
			);
		
	}
}
