package com.mnia.orderservice.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity @Getter
@Setter
@Table(name = "TB_ORDERS")
@AllArgsConstructor @NoArgsConstructor
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderNumber;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLineItems> OrderLineItemsList;
}
