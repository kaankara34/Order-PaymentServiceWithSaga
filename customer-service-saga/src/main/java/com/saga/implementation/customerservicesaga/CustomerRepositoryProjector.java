package com.saga.implementation.customerservicesaga;



import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saga.implementation.commandsandevents.CustomerCreatedEvent;
import com.saga.implementation.commandsandevents.InvoiceCreatedEvent;

@Service
public class CustomerRepositoryProjector {
	
	private final CustomerRepository customerRepository;

	
	@Autowired
	public CustomerRepositoryProjector(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}
	
	
	@EventHandler//This is different than @EventSourcingHandler in Aggregate classes.Because it stores the data in database.However,@EventSourcingHandler stores data in event store
	public void on(InvoiceCreatedEvent invoiceCreatedEvent){
		
     Customer customer = customerRepository.findById(invoiceCreatedEvent.customerId).get();
     customer.setBudget(customer.getBudget().subtract(invoiceCreatedEvent.price));
     customerRepository.save(customer);
		
	}
	
	@EventHandler//This is different than @EventSourcingHandler in Aggregate classes.Because it stores the data in database.However,@EventSourcingHandler stores data in event store
	public void on(CustomerCreatedEvent customerCreatedEvent) {
		
		customerRepository.save(new Customer(customerCreatedEvent.customerId,customerCreatedEvent.budget));
		
		
		
		
	}
	
	

}
