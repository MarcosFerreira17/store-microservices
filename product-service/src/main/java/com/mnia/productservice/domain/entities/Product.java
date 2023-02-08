package com.mnia.productservice.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value = "TB_PRODUCT")
@AllArgsConstructor @NoArgsConstructor
@Builder @Data
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private Integer likesCounter;
    private String image;
    private String comments;
    private BigDecimal price;
    private Boolean status;
}
