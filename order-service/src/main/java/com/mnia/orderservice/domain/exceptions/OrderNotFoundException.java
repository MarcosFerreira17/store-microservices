package com.mnia.orderservice.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends Exception{
    private static final long serialVersionUID = 9009500269607484034L;

    public OrderNotFoundException(String idOrder){
        super(String.format("Order id: {} not found.", idOrder));
    }
}
