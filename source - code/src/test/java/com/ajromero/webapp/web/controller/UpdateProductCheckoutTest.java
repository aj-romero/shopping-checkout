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

@DisplayName("Update Quantity Product to Checkout Controller test")
class UpdateProductCheckoutTest {

    @Mock
    ICheckoutService checkoutService;

    @InjectMocks
    CheckoutController checkoutController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Update Quantity product to Checkout success")
    void whenUpdateQuantityProductToCheckout_thenAssert() {
        CheckoutBasicDto dtoBasicCk = new CheckoutBasicDto();
        CheckoutProductDto dtoCkProduct = new CheckoutProductDto();
        dtoCkProduct.setIdProduct(1L);
        when(checkoutService.updateQuantityProduct(anyLong(),any())).thenReturn(dtoBasicCk);

        ResponseEntity<CheckoutBasicDto> result = checkoutController.updateQuantityProduct(1l,dtoCkProduct);
        ResponseEntity<CheckoutBasicDto> expected = ResponseEntity.
                status(HttpStatus.OK).body(dtoBasicCk);

        verify(checkoutService).updateQuantityProduct(anyLong(),any());
        assertThat(result, samePropertyValuesAs(expected));
    }

}