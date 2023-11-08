package com.ajromero.webapp.web.controller;

import com.ajromero.webapp.persistence.domain.Product;
import com.ajromero.webapp.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.mockito.Mockito.*;

@DisplayName("Product list controller test")
class ProductListControllerTest {

    @Mock
    ProductService productService;

    @InjectMocks
    ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenFetchProducts_thenAssert() {
        List<Product> products = new ArrayList<>();
        when(productService.findAll()).thenReturn(products);

        ResponseEntity<List<Product>> result = productController.getProducts();
        ResponseEntity<List<Product>> expected = ResponseEntity.ok(products);

        verify(productService).findAll();
        assertThat(result,samePropertyValuesAs(expected));
    }

}