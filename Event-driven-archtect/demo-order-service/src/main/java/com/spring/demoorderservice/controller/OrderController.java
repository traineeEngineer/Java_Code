package com.spring.demoorderservice.controller;

import com.spring.demoorderservice.dto.OrderRequest;
import com.spring.demoorderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService oService;

    @PostMapping("/place")
    @CircuitBreaker(name = "inventory",fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest){
        oService.saveOrder(orderRequest);
        return  CompletableFuture.supplyAsync(()->"order place successfully");
    }

    public CompletableFuture<String>fallbackMethod(OrderRequest orderRequest,RuntimeException ex) {

        return CompletableFuture.supplyAsync(()->"Oops something is wrong please order some time");
    }
}
