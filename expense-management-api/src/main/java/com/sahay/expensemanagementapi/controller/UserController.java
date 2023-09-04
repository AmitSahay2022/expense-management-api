package com.sahay.expensemanagementapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahay.expensemanagementapi.entity.User;
import com.sahay.expensemanagementapi.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
   private UserService userService;
   @PostMapping
   public ResponseEntity<User> saveUser(@RequestBody User user){
	   return new ResponseEntity<User>(userService.saveUser(user),HttpStatus.CREATED);
   }
   @PutMapping
   public ResponseEntity<User> updateUser(@RequestBody User user){
	   return new ResponseEntity<User>(userService.updateUser(user),HttpStatus.OK);
   }
   @DeleteMapping
   public ResponseEntity<String> deleteUser(){
	   return new ResponseEntity<String>(userService.deleteUser(),HttpStatus.OK);
   }
   @GetMapping
   public ResponseEntity<User> getById(){
	   return new ResponseEntity<User>(userService.findById(),HttpStatus.OK);
   }
   
}
