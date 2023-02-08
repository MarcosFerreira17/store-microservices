package com.mnia.orderservice.domain.dtos.order;

import com.mnia.orderservice.domain.dtos.Meta;
import com.mnia.orderservice.domain.dtos.ResponseDTO;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Getter @Slf4j
public class OrderListDTO extends ResponseDTO {
    private List<OrderDetailDTO> orderList = new ArrayList<>();

    public OrderListDTO(String id, String created, String kind, Meta meta, List<OrderDetailDTO> orderEntitiesList){
        super(id, created, kind, meta);
        this.orderList = orderEntitiesList;
    }
}
