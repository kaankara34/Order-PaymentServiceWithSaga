package com.saga.implementation.commandsandevents;

import java.math.BigDecimal;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CreateOrderCommand {

	/*
	 * This command is triggered when the client posts createOrder request.If you
	 * pay attention this command is not sent in Saga
	 * cycle(OrderManagementSaga.java),rather it is triggered in
	 * OrderCommandService.java. Therefore,this command is not sent by
	 * orchestrator,but when this command is handled,OrderCreatedEvent is published
	 * and Saga starts with this event.
	 */

	@TargetAggregateIdentifier
	public final String orderId;
	public final String customerId;
	public final BigDecimal price;
	public final OrderStatus status;

	public CreateOrderCommand(String orderId, String customerId, BigDecimal price, OrderStatus status) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.price = price;
		this.status = status;
	}

}
