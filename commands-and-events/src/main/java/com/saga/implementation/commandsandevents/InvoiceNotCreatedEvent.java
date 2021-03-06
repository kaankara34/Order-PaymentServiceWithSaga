package com.saga.implementation.commandsandevents;

import java.math.BigDecimal;

public class InvoiceNotCreatedEvent {

	/*
	 * COMMON MISTAKE!We dont put @TargetAggregateIdentifier in events.Only putting
	 * in commands are enough.
	 */
	public final BigDecimal price;
	public final String customerId;
	public final String orderId;

	public InvoiceNotCreatedEvent(BigDecimal price, String customerId, String orderId) {

		this.price = price;
		this.customerId = customerId;
		this.orderId = orderId;
	}

}
