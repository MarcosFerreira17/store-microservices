package com.mnia.productservice.domain.service;

import com.mnia.productservice.domain.dtos.product.ProductDTO;
import com.mnia.productservice.domain.entities.Product;
import com.mnia.productservice.domain.exceptions.ProductNotFoundException;
import com.mnia.productservice.domain.repositories.ProductRepository;
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

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private ProductDTO productDTO;
    private Product productEntity;
    private Page<ProductDTO> productDTOPage;
    private Pageable pageable;

    private static final String PRODUCT_ID = "PRODUCT_ID";
    private static final String NAME = "PRODUCT_NAME";
    private static final String DESCRIPTION = "PRODUCT_DESCRIPTION";
    private static final BigDecimal PRICE = BigDecimal.valueOf(10.0);
    private static final boolean STATUS = true;

    @BeforeEach
    public void setUp() {
        productDTO = ProductDTO.builder()
                .name(NAME)
                .description(DESCRIPTION)
                .price(PRICE)
                .status(STATUS)
                .build();

        productEntity = Product.builder()
                .id(PRODUCT_ID)
                .name(NAME)
                .description(DESCRIPTION)
                .price(PRICE)
                .status(STATUS)
                .build();

        productDTOPage = new PageImpl<>(List.of(productDTO));

        pageable = PageRequest.of(0, 10);
    }

    @Test
    public void createProduct_whenCalled_thenReturnProduct() {
        // given
        when(productRepository.save(any(Product.class))).thenReturn(productEntity);

        // when
        productService.createProduct(productDTO);

        Product product = Product.builder()
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .status(productDTO.getStatus()).build();

        // then
        verify(productRepository).save(product);
    }

    @Test
    public void getProductList_whenCalled_thenReturnProductList() {
        // given
        when(productRepository.getProductList(pageable)).thenReturn(Optional.of(productDTOPage));

        // when
        Page<ProductDTO> result = productService.getProductList(pageable);

        // then
        assertEquals(productDTOPage, result);
        verify(productRepository).getProductList(pageable);
    }

    @Test
    public void getProductDetailById_whenCalledWithValidId_thenReturnProductDetail() throws ProductNotFoundException {
        // given
        when(productRepository.getProductDetailById(PRODUCT_ID)).thenReturn(Optional.of(productDTO));

        // when
        Optional<ProductDTO> result = productService.getProductDetailById(PRODUCT_ID);

        // then
        assertEquals(Optional.of(productDTO), result);
        verify(productRepository).getProductDetailById(PRODUCT_ID);
    }

    @Test
    void testUpdateProductById() throws ProductNotFoundException {
        when(productRepository.getProductDetailById(PRODUCT_ID)).thenReturn(Optional.of(productDTO));
        productService.updateProductById(PRODUCT_ID, productDTO);
        verify(productRepository).save(any(Product.class));
    }

    @Test
    void testUpdateProductByIdNotFound() {
        when(productRepository.getProductDetailById(PRODUCT_ID)).thenReturn(Optional.empty());
        Throwable thrown = catchThrowable(() -> productService.updateProductById(PRODUCT_ID, productDTO));
        assertThat(thrown).isInstanceOf(ProductNotFoundException.class);
    }

    @Test
    void testDeleteProductById() throws ProductNotFoundException {
        when(productRepository.getProductDetailById(PRODUCT_ID)).thenReturn(Optional.of(productDTO));
        productService.deleteProductById(PRODUCT_ID);
        verify(productRepository).delete(any(Product.class));
    }

    @Test
    void testDeleteProductByIdNotFound() {
        when(productRepository.getProductDetailById(PRODUCT_ID)).thenReturn(Optional.empty());
        Throwable thrown = catchThrowable(() -> productService.deleteProductById(PRODUCT_ID));
        assertThat(thrown).isInstanceOf(ProductNotFoundException.class);
    }
}
