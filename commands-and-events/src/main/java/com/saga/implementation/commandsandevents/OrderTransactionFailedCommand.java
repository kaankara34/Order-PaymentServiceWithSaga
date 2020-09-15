package com.saga.implementation.commandsandevents;

import java.math.BigDecimal;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class OrderTransactionFailedCommand {
	/*
	 * "When orchestrator sends command,it uses
	 * SagaLifecycle.associateWith("key",object); for this example "key" must be
	 * "orderId" and the object must be invoiceNotCreatedEvent.orderId which is the
	 * previous event that is published before
	 * OrderTransactionFailedCommand.Basically,"object" that is associated with "key" is
	 * always taken from the previous event to trigger next the next command.(Check OrderManagementSaga.java!)
	 */
	@TargetAggregateIdentifier
    public final String orderId;
    public final String customerId;
    public final BigDecimal price;
    private OrderStatus status;
	public OrderTransactionFailedCommand(String orderId, String customerId, BigDecimal price) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.price = price;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	
	

}
