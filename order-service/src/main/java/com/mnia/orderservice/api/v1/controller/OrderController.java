package com.mnia.orderservice.api.v1.controller;

import com.mnia.orderservice.domain.dtos.order.OrderDetailDTO;
import com.mnia.orderservice.domain.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getOrder(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC)
                                               Pageable pageable){
        final var pageOrdersList = orderService.getOrderList(pageable);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> placeOrder(@RequestBody OrderDetailDTO orderDetailDto){
        orderService.placeOrder(orderDetailDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Order created Successfully");
    }
}
