package com.mnia.productservice.domain.dtos.product;

import com.mnia.productservice.domain.dtos.Meta;
import com.mnia.productservice.domain.dtos.ResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ProductListDTO extends ResponseDTO {
    private List<ProductDTO> productList = new ArrayList<>();

    public ProductListDTO(String id, String created, String kid, Meta meta, List<ProductDTO> productEntities){
        super(id, created, kid, meta);
        this.productList = productEntities;
    }
}
