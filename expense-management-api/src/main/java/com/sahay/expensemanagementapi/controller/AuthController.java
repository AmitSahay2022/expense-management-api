package com.sahay.expensemanagementapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahay.expensemanagementapi.dto.LoginModel;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
	
	private AuthenticationManager authenticationManager;
	
    @PostMapping("/login")
	public ResponseEntity<HttpStatus> login(@RequestBody LoginModel loginModel){
    	
    	Authentication authentication=
    			authenticationManager.authenticate(
    					new UsernamePasswordAuthenticationToken(
    							loginModel.getUsername(), loginModel.getPassword()));
    	
    	SecurityContextHolder.getContext().setAuthentication(authentication);
    	
		return new ResponseEntity<>(HttpStatus.OK);
	}
    
}
