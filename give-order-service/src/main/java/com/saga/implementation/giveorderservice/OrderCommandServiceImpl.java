package com.saga.implementation.giveorderservice;

import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.command.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saga.implementation.commandsandevents.CreateOrderCommand;
import com.saga.implementation.commandsandevents.OrderStatus;

@Service
public class OrderCommandServiceImpl implements OrderCommandService {

	@Autowired
	private OrderRepository orderRepository;

	private final CommandGateway commandGateway;

	public OrderCommandServiceImpl(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}

	@Override // This command is not sent by orchestrator as you can see.It is sent upon the
				// post request of client,however the saga will start after this command is
				// handled and OrderCreatedEvent published.
	public CompletableFuture<String> createOrder(OrderCreateDTO orderCreateDTO) {
		return commandGateway.send(new CreateOrderCommand(orderCreateDTO.getOrderId(), orderCreateDTO.getCustomerId(),
				orderCreateDTO.getPrice(), OrderStatus.CREATED));
	}

}