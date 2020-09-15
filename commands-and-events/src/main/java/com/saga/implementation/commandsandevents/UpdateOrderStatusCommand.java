package com.saga.implementation.commandsandevents;

import java.math.BigDecimal;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class UpdateOrderStatusCommand {
	
	/*
	 * "When orchestrator sends command,it uses
	 * SagaLifecycle.associateWith("key",object); for this example "key" must be
	 * "orderId" and the object must be invoiceCreatedEvent.orderId which is the
	 * previous event that is published before
	 * UpdateOrderStatusCommand.Basically,"object" that is associated with "key" is
	 * always taken from the previous event to trigger next the next command.(Check OrderManagementSaga.java!)
	 */
	
	
	 @TargetAggregateIdentifier
	    public final String orderId;
	    private OrderStatus orderStatus;
	    public final BigDecimal price;
	    public final String customerıd;
		
		public UpdateOrderStatusCommand(String orderId, BigDecimal price, String customerıd) {
			super();
			this.orderId = orderId;
			this.price = price;
			this.customerıd = customerıd;
		}
		public OrderStatus getOrderStatus() {
			return orderStatus;
		}
		public void setOrderStatus(OrderStatus orderStatus) {
			this.orderStatus = orderStatus;
		}
		
	

}
