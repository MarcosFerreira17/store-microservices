package com.mnia.productservice.api.v1.controllers.product;

import com.mnia.productservice.domain.dtos.Meta;
import com.mnia.productservice.domain.dtos.product.ProductDTO;
import com.mnia.productservice.domain.dtos.product.ProductListDTO;
import com.mnia.productservice.domain.exceptions.ProductNotFoundException;
import com.mnia.productservice.domain.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductListDTO> getProductsList(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC)
                                                              Pageable pageable) {
        final var pageProductList = productService.getProductList(pageable);
        var meta = new Meta(
                pageProductList.getNumber() + 1,
                pageProductList.getSize(),
                pageProductList.getTotalPages(),
                pageProductList.getTotalElements()
        );

        final var productList = new ProductListDTO(
                UUID.randomUUID().toString(),
                LocalDateTime.now().toString(),
                "Products#List", meta, pageProductList.getContent());
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getProductsDetailById(@PathVariable("id") String id) throws ProductNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductDetailById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createProduct(@RequestBody @Valid ProductDTO productDTO){
        productService.createProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product created successfully");
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> putProductById(@PathVariable("id") String id,@RequestBody @Valid ProductDTO productDTO) throws ProductNotFoundException {
        productService.updateProductById(id, productDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully updated.");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> DeleteProductById(@PathVariable("id") String id) throws ProductNotFoundException {
        productService.deleteProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted.");
    }
}
