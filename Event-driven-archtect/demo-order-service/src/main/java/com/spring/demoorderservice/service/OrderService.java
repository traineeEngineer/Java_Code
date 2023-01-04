package com.spring.demoorderservice.service;

import com.spring.demoorderservice.dto.InventoryResponse;
import com.spring.demoorderservice.dto.OrderLineItemsDto;
import com.spring.demoorderservice.dto.OrderRequest;
import com.spring.demoorderservice.model.Order;
import com.spring.demoorderservice.model.OrderLineItems;
import com.spring.demoorderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository oRepo;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private Tracer tracer;

    public void saveOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());


        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream().map(this::mapToDto)
                .collect(Collectors.toList());


        order.setOrderLineItemsList(orderLineItems);


        List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .collect(Collectors.toList());

        Span inventoryServiceLookup = tracer.nextSpan().name("inventoryServiceLookup");
        try (Tracer.SpanInScope spanInScope= tracer.withSpan(inventoryServiceLookup.start())) {
            InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get().uri("http://INVENTORY-SERVICE/api/invent/",
                            uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                    .retrieve().bodyToMono(InventoryResponse[].class)
                    .block();


            boolean allProductsIsInStock = Arrays.stream(inventoryResponseArray)
                    .allMatch(InventoryResponse::isInStock);


            if (allProductsIsInStock) {
                oRepo.save(order);
            } else {
                throw new IllegalArgumentException("product not available please try again..... ");
            }
        } finally {
            inventoryServiceLookup.end();

        }

    }
    private OrderLineItems  mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems items=new OrderLineItems();
        items.setPrice(orderLineItemsDto.getPrice());
        items.setQuantity(orderLineItemsDto.getQuantity());
        items.setSkuCode(orderLineItemsDto.getSkuCode());
        return items;
    }
}
