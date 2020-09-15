package com.saga.implementation.giveorderservice;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.saga.implementation.commandsandevents.OrderStatus;

@Entity
@Table(name = "orderEntity")
public class Order {

	@Id
	private String orderId;

	private BigDecimal price;

	private OrderStatus orderStatus;

	private String customerId;

	public Order(String orderId, BigDecimal price, OrderStatus orderStatus, String customerId) {
		super();
		this.orderId = orderId;
		this.price = price;
		this.orderStatus = orderStatus;
		this.customerId = customerId;
	}

	public Order() {
		super();
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}
