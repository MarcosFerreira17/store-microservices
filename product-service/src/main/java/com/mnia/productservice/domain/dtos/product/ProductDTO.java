package com.mnia.productservice.domain.dtos.product;

import com.mnia.productservice.domain.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
public class ProductDTO {
    @NotBlank(message = "Name must be filled")
    private String name;
    @NotBlank(message = "Description must be filled")
    private String description;
    private BigDecimal price;
    private Boolean status;

    public ProductDTO(Product product){
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        status = product.getStatus();
    }
}
