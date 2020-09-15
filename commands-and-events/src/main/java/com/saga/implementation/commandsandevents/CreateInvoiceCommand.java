package com.saga.implementation.commandsandevents;

import java.math.BigDecimal;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CreateInvoiceCommand {

	/*
	 * "When orchestrator sends command,it uses
	 * SagaLifecycle.associateWith("key",object); for this example "key" must be
	 * "customerId" and the object must be orderCreatedEvent.customerId which is the
	 * previous event that is published before
	 * CreateInvoiceCommand.Basically,"object" that is associated with "key" is
	 * always taken from the previous event to trigger next the next command.(Check OrderManagementSaga.java!)
	 */

	@TargetAggregateIdentifier
	public final String customerId;
	public final BigDecimal price;
	public final String orderId;

	public CreateInvoiceCommand(String customerId, BigDecimal price, String orderId) {
		this.customerId = customerId;
		this.price = price;
		this.orderId = orderId;
	}

}
