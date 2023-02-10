package com.mnia.orderservice.domain.services;

import com.mnia.orderservice.domain.dtos.InventoryResponse;
import com.mnia.orderservice.domain.dtos.order.OrderDetailDTO;
import com.mnia.orderservice.domain.dtos.order.OrderLineItemsDTO;
import com.mnia.orderservice.domain.entities.Order;
import com.mnia.orderservice.domain.entities.OrderLineItems;
import com.mnia.orderservice.domain.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service @Slf4j
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private static final String REQUEST = "request all products";
    private static final String CORRELATION_ID = "correlationID";
    private final WebClient.Builder webClientBuilder;

    @Transactional
    public void placeOrder(OrderDetailDTO orderDetailDto){
        MDC.put(CORRELATION_ID, UUID.randomUUID().toString());
        log.info(REQUEST);

        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        
        List<OrderLineItems> orderLineItems = orderDetailDto.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();
        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

        //Call the inventory service, and place order if product is in stock
        InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
                .uri("http://inventory-service/api/v1/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allProductsInStock = Arrays.stream(inventoryResponseArray)
                .allMatch(InventoryResponse::getIsInStock);

        if (allProductsInStock){
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Product is not in stock, please try again later");
        }

    }

    private OrderLineItems mapToDto(OrderLineItemsDTO orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }

    public Page<Order> getOrderList(Pageable pageable) {
        MDC.put(CORRELATION_ID, UUID.randomUUID().toString());
        log.info(REQUEST);

        return orderRepository.findAll(pageable);
    }
}
