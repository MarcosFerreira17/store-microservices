package com.mnia.productservice.domain.repository;

import com.mnia.productservice.domain.dtos.product.ProductDTO;
import com.mnia.productservice.domain.entities.Product;
import com.mnia.productservice.domain.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void getProductList() {
        Pageable pageable = PageRequest.of(0, 10);
        Optional<Page<ProductDTO>> productList = productRepository.getProductList(pageable);
        assertThat(productList).isNotEmpty();
    }

    @Test
    void getProductDetailById() {
        String id = "testId";
        Product product = new Product();
        product.setId(id);
        productRepository.save(product);
        Optional<ProductDTO> productDetail = productRepository.getProductDetailById(id);
        assertThat(productDetail).isNotEmpty();
    }
}
