package com.sahay.expensemanagementapi.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
@Entity
@Table(name="expenses")
public class Expense {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;
	@NotBlank(message = "name should not blank")
	@Size(min = 3,message = "must be atleast 3 character")
   private String name;
   private String description;
   @NotNull
   private BigDecimal amount;
   private String category;
   private Date date;
   @CreationTimestamp
   private Timestamp createdAt;
   @UpdateTimestamp
   private Timestamp updatedAt;
   
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="user_id", nullable = false)
   @OnDelete(action = OnDeleteAction.CASCADE)
   @JsonIgnore
   private User user;
}
