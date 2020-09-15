package com.saga.implementation.commandsandevents;

import java.math.BigDecimal;


public class OrderUpdatedEvent {
	/*
	 * COMMON MISTAKE! We dont put @TargetAggregateIdentifier in events.Only putting
	 * in commands are enough.
	 */
	public final String orderId;

    public final OrderStatus orderStatus;
    public final String customerId;
  
	public OrderUpdatedEvent(String orderId, String customerId,OrderStatus orderStatus) {
		super();
		this.orderId = orderId;
		this.orderStatus = orderStatus;
		this.customerId = customerId;
		
	}
    
	
	
	

}
