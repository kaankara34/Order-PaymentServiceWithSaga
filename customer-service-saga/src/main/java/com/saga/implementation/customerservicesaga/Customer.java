package com.saga.implementation.customerservicesaga;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;



@Entity
public class Customer {

	@Id
	private String customerId;

	private BigDecimal budget;

	public Customer(String customerId, BigDecimal budget) {
		super();
		this.customerId = customerId;
		this.budget = budget;

	}

	public Customer() {
		super();
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getBudget() {
		return budget;
	}

	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}

}
