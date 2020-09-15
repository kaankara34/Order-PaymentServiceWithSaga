package com.saga.implementation.commandsandevents;

import java.math.BigDecimal;

public class OrderCreatedEvent {
	/*
	 * COMMON MISTAKE! We dont put @TargetAggregateIdentifier in events.!Only putting
	 * in commands are enough.
	 */
	public final String orderId;
	public final BigDecimal price;
	public final String customerId;
	public final OrderStatus status;
	public OrderCreatedEvent(String orderId, BigDecimal price, String customerId, OrderStatus status) {
		super();
		this.orderId = orderId;
		this.price = price;
		this.customerId = customerId;
		this.status = status;
	}

	

}
