package com.ajromero.webapp.web.controller;

import com.ajromero.webapp.service.interfaces.ICheckoutService;
import com.ajromero.webapp.web.dto.CheckoutBasicDto;
import com.ajromero.webapp.web.dto.CheckoutProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.mockito.Mockito.*;

@DisplayName("Delete Product - Checkout Controller test")
class DeleteProductCheckoutTest {

    @Mock
    ICheckoutService checkoutService;

    @InjectMocks
    CheckoutController checkoutController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Delete product to Checkout success")
    void whenDeleteProductToCheckout_thenAssert() {
        doNothing().when(checkoutService).deleteCheckoutProduct(anyLong(),anyLong());

        checkoutController.deleteCheckoutProduct(1l,1l);

        verify(checkoutService).deleteCheckoutProduct(anyLong(),any());
    }

}