//package com.mnia.productservice.domain.exception;
//
//import com.mnia.productservice.domain.exceptions.ProductNotFoundException;
//import org.junit.jupiter.api.Test;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.catchThrowable;
//
//class ProductNotFoundExceptionTest {
//    @Test
//    void testExceptionMessage() {
//        String idProduct = "testId";
//        Throwable thrown = catchThrowable(() -> {
//            throw new ProductNotFoundException(idProduct);
//        });
//        assertThat(thrown).isInstanceOf(ProductNotFoundException.class)
//                .hasMessage("Product id: testId not found.");
//    }
//}
