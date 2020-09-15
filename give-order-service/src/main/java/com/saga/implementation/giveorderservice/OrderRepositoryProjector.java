package com.saga.implementation.giveorderservice;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saga.implementation.commandsandevents.OrderCreatedEvent;
import com.saga.implementation.commandsandevents.OrderFailedEvent;
import com.saga.implementation.commandsandevents.OrderStatus;
import com.saga.implementation.commandsandevents.OrderUpdatedEvent;

@Service
public class OrderRepositoryProjector {
	
	private final OrderRepository repository;

	
	@Autowired
	public OrderRepositoryProjector(OrderRepository repository) {
		super();
		this.repository = repository;
	}
	
	
	@EventHandler//This is different than @EventSourcingHandler in Aggregate classes.Because it stores the data in database.However,@EventSourcingHandler stores data in event store
	public void on (OrderCreatedEvent orderCreatedEvent) {
		
		Order order = new Order(orderCreatedEvent.orderId, orderCreatedEvent.price,orderCreatedEvent.status, orderCreatedEvent.customerId);
		repository.save(order);
		
		
	}
	
	

	@EventHandler//This is different than @EventSourcingHandler in Aggregate classes.Because it stores the data in database.However,@EventSourcingHandler stores data in event store
	public void on (OrderUpdatedEvent orderUpdatedEvent) {
		
		Order order = repository.findById(orderUpdatedEvent.orderId).get();
		order.setOrderStatus(orderUpdatedEvent.orderStatus);
		repository.save(order);
		
	}
	

	@EventHandler//This is different than @EventSourcingHandler in Aggregate classes.Because it stores the data in database.However,@EventSourcingHandler stores data in event store
	public void on (OrderFailedEvent orderFailedEvent) {
		
		Order order = repository.findById(orderFailedEvent.orderId).get();
		order.setOrderStatus(orderFailedEvent.orderStatus);
		repository.save(order);
		
	}
	
	
	
	
	

}
