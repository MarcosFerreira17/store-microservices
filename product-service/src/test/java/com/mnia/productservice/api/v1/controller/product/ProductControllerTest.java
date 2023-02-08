package com.mnia.productservice.api.v1.controller.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mnia.productservice.api.v1.controllers.product.ProductController;
import com.mnia.productservice.domain.dtos.product.ProductDTO;
import com.mnia.productservice.domain.dtos.product.ProductListDTO;
import com.mnia.productservice.domain.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    private MockMvc mockMvc;
    private static final Pageable pageable = PageRequest.of(0,10);
    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController)
                .build();
    }

    @Test
    void shouldCreateANewProduct() throws Exception {
        ProductDTO productDTO = ProductDTO.builder().build();
        mockMvc.perform(post("/api/v1/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(productDTO)))
                .andExpect(status().isCreated());
        verify(productService, times(1)).createProduct(productDTO);
    }

    @Test
    void shouldReturnProductsList() {
        List<ProductDTO> productDTOS = Arrays.asList(new ProductDTO("product 1", "product 1 description", BigDecimal.valueOf(10), true),
                new ProductDTO("product 2", "product 2 description", BigDecimal.valueOf(10), true));
        Page<ProductDTO> pageProductList = new PageImpl<>(productDTOS);
        when(productService.getProductList(any(Pageable.class))).thenReturn(pageProductList);

        ResponseEntity<ProductListDTO> response = productController.getProductsList(pageable);

        assertEquals(ResponseEntity.ok().build().getStatusCode(), response.getStatusCode());
        assertEquals(2, response.getBody().getMeta().getTotalRecords());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
