package com.sahay.expensemanagementapi.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	@Column(unique = true)
	@Email
	private String email;
	@NotBlank
	
	private String password;
	private int age;
	@CreationTimestamp
	@Column(updatable = false)
	private Timestamp createdAt;
	@UpdateTimestamp
	private Timestamp updatedAt;

}
