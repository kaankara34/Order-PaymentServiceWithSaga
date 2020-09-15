package com.saga.implementation.giveorderservice;

import java.math.BigDecimal;

public class OrderCreateDTO {

	private String orderId;
	private String customerId;
	private BigDecimal price;


	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}