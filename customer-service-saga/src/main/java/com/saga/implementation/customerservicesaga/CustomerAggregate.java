package com.saga.implementation.customerservicesaga;

import java.math.BigDecimal;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.saga.implementation.commandsandevents.CreateCustomerCommand;
import com.saga.implementation.commandsandevents.CreateInvoiceCommand;
import com.saga.implementation.commandsandevents.CustomerCreatedEvent;
import com.saga.implementation.commandsandevents.InvoiceCreatedEvent;
import com.saga.implementation.commandsandevents.InvoiceNotCreatedEvent;

@Aggregate //This is so similar to Entity class.However, it does not store the parameters in database.It stores in event store!
public class CustomerAggregate {

	@AggregateIdentifier //Like @Id in entity class.However,it stores in event store.
	private String customerId;

	private BigDecimal budget;

	private String orderId;

	public CustomerAggregate(String customerId, BigDecimal budget, String orderId) {
		super();
		this.customerId = customerId;
		this.budget = budget;
		this.orderId = orderId;
	}

	public CustomerAggregate() {
		super();
	}

	@CommandHandler//Handles the commands send by orchestrator and publishes new event!
	public CustomerAggregate(CreateCustomerCommand command) {

		AggregateLifecycle.apply(new CustomerCreatedEvent(command.customerId, command.budget));

	}

	@CommandHandler//Handles the commands send by orchestartor and publishes new event!
	public void createInvoice(CreateInvoiceCommand createInvoiceCommand) {//If there is enough balance in customers account the invoice will created.
		if (createInvoiceCommand.price.compareTo(budget) < 0) {

			AggregateLifecycle.apply(new InvoiceCreatedEvent(createInvoiceCommand.price,
					createInvoiceCommand.customerId, createInvoiceCommand.orderId));

		} else {

			AggregateLifecycle.apply(new InvoiceNotCreatedEvent(createInvoiceCommand.price,
					createInvoiceCommand.customerId, createInvoiceCommand.orderId));
		}

	}

	@EventSourcingHandler//Events that are published by @CommandHandler above are saved to event store here!(Not in database! but in event store.)
	protected void on(InvoiceCreatedEvent invoiceCreatedEvent) {

		this.customerId = invoiceCreatedEvent.customerId;
		this.budget = budget.subtract(invoiceCreatedEvent.price);
		this.orderId = invoiceCreatedEvent.orderId;

	}

	@EventSourcingHandler//Events that are published by @CommandHandler above are saved to event store here!(Not in database! but in event store.)
	protected void on(CustomerCreatedEvent customerCreatedEvent) {

		this.budget = customerCreatedEvent.budget;
		this.customerId = customerCreatedEvent.customerId;

	}

}