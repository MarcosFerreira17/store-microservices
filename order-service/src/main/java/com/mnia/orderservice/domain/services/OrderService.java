package com.mnia.orderservice.domain.services;

import com.mnia.orderservice.domain.dtos.order.OrderDetailDTO;
import com.mnia.orderservice.domain.dtos.order.OrderLineItemsDTO;
import com.mnia.orderservice.domain.entities.Order;
import com.mnia.orderservice.domain.entities.OrderLineItems;
import com.mnia.orderservice.domain.repositories.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service @Slf4j
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    private static final String REQUEST = "request all products";
    private static final String CORRELATION_ID = "correlationID";

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

        orderRepository.save(order);
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
