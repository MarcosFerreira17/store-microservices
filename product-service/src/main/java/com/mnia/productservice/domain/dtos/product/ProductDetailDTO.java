package com.mnia.productservice.domain.dtos.product;

import com.mnia.productservice.domain.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDetailDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private BigDecimal price;
    @NotBlank @Size(max = 5)
    private Boolean status;

    public ProductDetailDTO(Product product){
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
    }
}