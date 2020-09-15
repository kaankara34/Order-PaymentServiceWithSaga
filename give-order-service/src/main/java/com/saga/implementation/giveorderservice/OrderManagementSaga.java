package com.saga.implementation.giveorderservice;

import javax.inject.Inject;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import com.saga.implementation.commandsandevents.CreateCustomerCommand;
import com.saga.implementation.commandsandevents.CreateInvoiceCommand;
import com.saga.implementation.commandsandevents.CustomerCreatedEvent;
import com.saga.implementation.commandsandevents.InvoiceCreatedEvent;
import com.saga.implementation.commandsandevents.InvoiceNotCreatedEvent;
import com.saga.implementation.commandsandevents.OrderCreatedEvent;
import com.saga.implementation.commandsandevents.OrderTransactionFailedCommand;
import com.saga.implementation.commandsandevents.OrderUpdatedEvent;
import com.saga.implementation.commandsandevents.UpdateOrderStatusCommand;

@Saga//Here we have all the SAGA management.This the ORCHESTRATOR located in order service.
public class OrderManagementSaga {

    @Inject
    private transient CommandGateway commandGateway;
   
    

    @StartSaga//Saga cycle starts by consuming orderCreatedEvent
    @SagaEventHandler(associationProperty = "orderId")//This association property matches with @TargetAggregateIdentifier of CreateOrderCommand
    public void handle(OrderCreatedEvent orderCreatedEvent){

        
       SagaLifecycle.associateWith("customerId", orderCreatedEvent.customerId);//This "customerId" key is @TargetAggregateIdentifier of below (CreateInvoiceCommand) command.
       /*
   	 * "When orchestrator sends command,it uses
   	 * SagaLifecycle.associateWith("key",object); for this example "key" must be
   	 * "customerId" and the object must be orderCreatedEvent.customerId which is the
   	 * previous event that is published before
   	 * CreateInvoiceCommand.Basically,"object" that is associated with "key" is
   	 * always taken from the previous event to trigger next the next command.
   	 */
     	
        	commandGateway.send(new CreateInvoiceCommand(orderCreatedEvent.customerId,orderCreatedEvent.price,orderCreatedEvent.orderId));
     

       
    }

    @SagaEventHandler(associationProperty = "customerId")//This association property matches with @TargetAggregateIdentifier of CreateInvoiceCommand
    public void handle(InvoiceCreatedEvent invoiceCreatedEvent){
      
        
        SagaLifecycle.associateWith("orderId",invoiceCreatedEvent.orderId);
        /*
       	 * "When orchestrator sends command,it uses
       	 * SagaLifecycle.associateWith("key",object); for this example "key" must be
       	 * "orderId" and the object must be invoiceCreatedEvent.orderId which is the
       	 * previous event that is published before
       	 * UpdateOrderStatusCommand.Basically,"object" that is associated with "key" is
       	 * always taken from the previous event to trigger next the next command.
       	 */
        
        commandGateway.send(new UpdateOrderStatusCommand(invoiceCreatedEvent.orderId, invoiceCreatedEvent.price, invoiceCreatedEvent.customerId));
       
        
        
    }
    
    @SagaEventHandler(associationProperty = "customerId")//This association property matches with @TargetAggregateIdentifier of CreateInvoiceCommand
    @EndSaga//After below command is sent,the saga will end because order is FAILED.(Busines logic of why it has failed is in CustomerAggregate.java)
    public void handle(InvoiceNotCreatedEvent invoiceNotCreatedEvent) {
    	
    	SagaLifecycle.associateWith("orderId",invoiceNotCreatedEvent.orderId);
    	 /*
       	 * "When orchestrator sends command,it uses
       	 * SagaLifecycle.associateWith("key",object); for this example "key" must be
       	 * "orderId" and the object must be invoiceNotCreatedEvent.orderId which is the
       	 * previous event that is published before
       	 * OrderTransactionFailedCommand.Basically,"object" that is associated with "key" is
       	 * always taken from the previous event to trigger next the next command.
       	 */
    	
    	commandGateway.send(new OrderTransactionFailedCommand(invoiceNotCreatedEvent.orderId, invoiceNotCreatedEvent.customerId, invoiceNotCreatedEvent.price));
    	
    	
    	
    }
    
    


    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderUpdatedEvent orderUpdatedEvent){
        SagaLifecycle.end();//When order status is updated from CREATED to APPROVED saga ends.Same with @EndSaga
    }
}