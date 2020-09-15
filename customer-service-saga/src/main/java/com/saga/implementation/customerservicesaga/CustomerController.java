package com.saga.implementation.customerservicesaga;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.saga.implementation.commandsandevents.CreateCustomerCommand;

@RestController
public class CustomerController {
	@Autowired
	private CustomerRepository repository;

	private CommandGateway commandGateway;

	public CustomerController(CommandGateway commandGateway) {
		super();
		this.commandGateway = commandGateway;
	}

	@PostMapping("/customers")
	public String createCustomer(@RequestBody CustomerDTO customerDTO) {

		commandGateway.send(new CreateCustomerCommand(customerDTO.getCustomerId(), customerDTO.getBudget())); //This command is not part of saga.
		                                                                                                     //It is just a basic CQRS(command-event) to create Customer
		return customerDTO.getCustomerId() + " is created.";

	}
	
	
	@GetMapping("customers/get/{id}")
	public Customer getCustomer(@PathVariable String id) {
		
		return repository.findById(id).orElse(null);
		
		
	}
	
	
	
	
}