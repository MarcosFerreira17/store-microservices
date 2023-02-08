package com.mnia.productservice.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends Exception{
    private static final long serialVersionUID = 9009500269607484034L;

    public ProductNotFoundException(String idProduct){
        super(String.format("Product id: {} not found.", idProduct));
    }
}
