package com.mnia.productservice.domain.services;

import com.mnia.productservice.domain.dtos.product.ProductDTO;
import com.mnia.productservice.domain.entities.Product;
import com.mnia.productservice.domain.exceptions.ProductNotFoundException;
import com.mnia.productservice.domain.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service @Slf4j
public class ProductService {

    private static final String REQUEST = "request all products";
    private static final String CORRELATION_ID = "correlationID";
    @Autowired
    private ProductRepository repository;

    @Transactional
    public void createProduct(ProductDTO productDTO){
        MDC.put(CORRELATION_ID, UUID.randomUUID().toString());
        log.info(REQUEST);

        Product product = Product.builder()
                        .name(productDTO.getName())
                                .description(productDTO.getDescription())
                                        .price(productDTO.getPrice())
                .status(productDTO.getStatus()).build();
        repository.save(product);
    }

    public Page<ProductDTO> getProductList(Pageable pageable){
        MDC.put(CORRELATION_ID, UUID.randomUUID().toString());
        log.info(REQUEST);
        return repository.getProductList(pageable).orElseThrow();
    }

    public Optional<ProductDTO> getProductDetailById(String id) throws ProductNotFoundException {
        MDC.put(CORRELATION_ID, UUID.randomUUID().toString());
        log.info(REQUEST);
        var productDB = repository.getProductDetailById(id);
        if (productDB.isEmpty()){
            throw new ProductNotFoundException(id);
        }
        return productDB;
    }

    public void updateProductById(String id, ProductDTO productDTO) throws ProductNotFoundException {
        MDC.put(CORRELATION_ID, UUID.randomUUID().toString());
        log.info(REQUEST);
        getProductDetailById(id);
        var productEntity = new Product();
        BeanUtils.copyProperties(productDTO, productEntity);
        productEntity.setId(id);
        repository.save(productEntity);
    }

    public void deleteProductById(String id) throws ProductNotFoundException  {
        MDC.put(CORRELATION_ID, UUID.randomUUID().toString());
        log.info(REQUEST);
        var productDB = getProductDetailById(id);
        var productEntity = new Product();
        BeanUtils.copyProperties(productDB, productEntity);
        productEntity.setId(id);
        repository.delete(productEntity);
    }
}
