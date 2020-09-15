package com.saga.implementation.commandsandevents;

public class OrderFailedEvent {
	/*
	 * COMMON MISTAKE! We dont put @TargetAggregateIdentifier in events.!Only putting
	 * in commands are enough.
	 */

	public final String orderId;

    public final OrderStatus orderStatus;
    public final String customerId;
  
	public OrderFailedEvent(String orderId, String customerId,OrderStatus orderStatus) {
		super();
		this.orderId = orderId;
		this.orderStatus = orderStatus;
		this.customerId = customerId;
		
	}

}
