package com.saga.implementation.commandsandevents;

import java.math.BigDecimal;

public class CustomerCreatedEvent {
	/*
	 * COMMON MISTAKE!We dont put @TargetAggregateIdentifier in events.Only putting
	 * in commands are enough.
	 */
	public final String customerId;
	public final BigDecimal budget;

	public CustomerCreatedEvent(String customerId, BigDecimal budget) {
		super();
		this.customerId = customerId;
		this.budget = budget;
	}

}
