package com.saga.implementation.giveorderservice;

import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.saga.implementation.commandsandevents.CreateCustomerCommand;


@RestController
public class OrderCommandController {
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	
	private OrderCommandService orderCommandService;

	private final CommandGateway commandGateway;

  




	public OrderCommandController(OrderCommandService orderCommandService, 
			CommandGateway commandGateway) {
		super();
		this.orderCommandService = orderCommandService;
		
		this.commandGateway = commandGateway;
	}


	@PostMapping("/orders")
    public CompletableFuture<String> createOrder(@RequestBody OrderCreateDTO orderCreateDTO){
        return orderCommandService.createOrder(orderCreateDTO);
    }
    
    
	@GetMapping("/orders/get/{id}")
    public Order getOrder(@PathVariable String id) {
    	
    	return orderRepository.findById(id).get();
    	
    	
    }
	
   
    
    
    

}
