package com.saga.implementation.giveorderservice;

import java.math.BigDecimal;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.saga.implementation.commandsandevents.CreateOrderCommand;
import com.saga.implementation.commandsandevents.OrderCreatedEvent;
import com.saga.implementation.commandsandevents.OrderFailedEvent;
import com.saga.implementation.commandsandevents.OrderStatus;
import com.saga.implementation.commandsandevents.OrderTransactionFailedCommand;
import com.saga.implementation.commandsandevents.OrderUpdatedEvent;
import com.saga.implementation.commandsandevents.UpdateOrderStatusCommand;

@Aggregate//This is so similar to Entity class.However, it does not store the parameters in database.It stores in event store!
public class OrderAggregate {

	@AggregateIdentifier//Like @Id in entity class.However,it stores in event store.
	private String orderId;

	private BigDecimal price;

	private OrderStatus orderStatus;

	private String customerId;

	public OrderAggregate() {
	}

	@CommandHandler//Handles the commands send by orchestrator and publishes new event!
	public OrderAggregate(CreateOrderCommand createOrderCommand) {
		AggregateLifecycle.apply(new OrderCreatedEvent(createOrderCommand.orderId, createOrderCommand.price,
				createOrderCommand.customerId, createOrderCommand.status));
	}

	@EventSourcingHandler//Events that are published by @CommandHandler above are saved to event store here!(Not in database! but in event store.)
	protected void on(OrderCreatedEvent orderCreatedEvent) {
		this.orderId = orderCreatedEvent.orderId;
		this.customerId = orderCreatedEvent.customerId;
		this.orderStatus = orderCreatedEvent.status;
		this.price = orderCreatedEvent.price;
	}

	@CommandHandler//Handles the commands send by orchestrator and publishes new event!
	protected void on(UpdateOrderStatusCommand updateOrderStatusCommand) {
		AggregateLifecycle.apply(new OrderUpdatedEvent(updateOrderStatusCommand.orderId,
				updateOrderStatusCommand.customerıd, OrderStatus.APPROVED));
	}

	@EventSourcingHandler//Events that are published by @CommandHandler above are saved to event store here!(Not in database! but in event store.)
	protected void on(OrderUpdatedEvent orderUpdatedEvent) {
		this.customerId = orderUpdatedEvent.customerId;
		this.orderId = orderUpdatedEvent.orderId;
		this.orderStatus = OrderStatus.APPROVED;

	}

	@CommandHandler//Handles the commands send by orchestrator and publishes new event!
	protected void on(OrderTransactionFailedCommand orderTransactionFailedCommand) {
		AggregateLifecycle.apply(new OrderFailedEvent(orderTransactionFailedCommand.orderId,
				orderTransactionFailedCommand.customerId, OrderStatus.REJECTED));

	}
	
	@EventSourcingHandler//Events that are published by @CommandHandler above are saved to event store here!(Not in database! but in event store.)
	public void on (OrderFailedEvent orderFailedEvent) {

		this.customerId = orderFailedEvent.customerId;
		this.orderId = orderFailedEvent.orderId;
		this.orderStatus = OrderStatus.REJECTED;

		
		
	}
	
	
	
	
	
	
	
	

}