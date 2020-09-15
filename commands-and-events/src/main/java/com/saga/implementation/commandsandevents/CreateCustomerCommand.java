package com.saga.implementation.commandsandevents;

import java.math.BigDecimal;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.modelling.saga.SagaLifecycle;

public class CreateCustomerCommand {

	/*
	 * This command is triggered when the client posts createCustomer request.If you
	 * pay attention this command is not sent in Saga
	 * cycle(OrderManagementSaga.java),rather it is triggered in
	 * CustomerController.java. Therefore,this command is not nothing to do with
	 * orchestrator.It is just a basic CQRS pattern.(event-command) to create a
	 * customer.
	 */
	@TargetAggregateIdentifier
	public final String customerId;
	public final BigDecimal budget;

	public CreateCustomerCommand(String customerId, BigDecimal budget) {
		super();
		this.customerId = customerId;
		this.budget = budget;
	}

}
