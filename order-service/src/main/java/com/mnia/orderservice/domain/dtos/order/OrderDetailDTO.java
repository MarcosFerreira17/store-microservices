package com.mnia.orderservice.domain.dtos.order;

import lombok.*;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    private List<OrderLineItemsDTO> orderLineItemsDtoList;
}
