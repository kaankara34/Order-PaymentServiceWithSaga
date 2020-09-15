package com.saga.implementation.customerservicesaga;

import java.math.BigDecimal;

public class CustomerDTO {
	
	private String customerId;

	private BigDecimal budget;

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
