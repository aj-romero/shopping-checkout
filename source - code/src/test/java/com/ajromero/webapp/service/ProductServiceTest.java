package com.ajromero.webapp.service;

import static org.junit.jupiter.api.Assertions.*;

import com.ajromero.webapp.persistence.domain.Product;
import com.ajromero.webapp.persistence.repositories.IProductRepository;
import com.ajromero.webapp.web.validation.IVerifyContent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Mockito.*;

@DisplayName("Product Service Test")
class ProductServiceTest {

    @Mock
    IProductRepository products;

    @Mock
    IVerifyContent verifyContent;

    @InjectMocks
    ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Fetch all products test")
    void whenFindAllsuccess_thenAssert() {
        List<Product> expected = new ArrayList<>();
        when(products.findAll()).thenReturn(expected);
        doNothing().when(verifyContent).verifyContent(eq(false),anyString());

        List<Product> result = productService.findAll();

        verify(products).findAll();
        assertThat(result,is(equalTo(expected)));

    }

    @Test
    @DisplayName("Find a product test")
    void wheFindAProductById_thenAssert() {
        Optional<Product> expected = Optional.empty();
        when(products.findById(anyLong())).thenReturn(expected);
        doNothing().when(verifyContent).verifyContent(eq(false),anyString());

        Optional<Product> result = productService.findById(anyLong());

        verify(products).findById(anyLong());
        assertThat(result,is(expected));
    }

}