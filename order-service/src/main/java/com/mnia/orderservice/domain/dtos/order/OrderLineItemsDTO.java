package com.mnia.orderservice.domain.dtos.order;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemsDTO {
    private Long id;
    @NotEmpty
    private String skuCode;
    @NotEmpty
    private BigDecimal price;
    @NotEmpty
    private Integer quantity;
}
