package com.saga.implementation.giveorderservice;

import java.util.concurrent.CompletableFuture;


import org.springframework.stereotype.Component;

@Component
public interface OrderCommandService {

    public CompletableFuture<String> createOrder(OrderCreateDTO orderCreateDTO);
   

}