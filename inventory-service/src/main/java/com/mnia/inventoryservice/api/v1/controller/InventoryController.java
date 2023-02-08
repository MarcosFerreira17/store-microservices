package com.mnia.inventoryservice.api.v1.controller;

import com.mnia.inventoryservice.domain.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("{sku-code}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@PathVariable("sku-code") String skuCode){
        return inventoryService.isInStock(skuCode);
    }
}
