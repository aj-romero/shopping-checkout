package com.ajromero.webapp.web.controller;

import com.ajromero.webapp.service.interfaces.ICheckoutService;
import com.ajromero.webapp.web.dto.CheckoutWithShippingDto;
import com.ajromero.webapp.web.dto.ShippingDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Update Shipping Address - Checkout Controller test")
class UpdateShippingAddressCheckoutTest {
    @Mock
    ICheckoutService checkoutService;

    @InjectMocks
    CheckoutController checkoutController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Update shipping address to Checkout success")
    void whenUpdateShippingAddressCheckout_thenAssert() {
        CheckoutWithShippingDto dtoShippingCk = new CheckoutWithShippingDto();
        ShippingDto dtoShipping = new ShippingDto();

        when(checkoutService.updateShippingAddress(anyLong(),any())).thenReturn(dtoShippingCk);

        ResponseEntity<CheckoutWithShippingDto> result = checkoutController.updateShippingAddress(1l,dtoShipping);
        ResponseEntity<CheckoutWithShippingDto> expected = ResponseEntity.
                status(HttpStatus.OK).body(dtoShippingCk);

        verify(checkoutService).updateShippingAddress(anyLong(),any());
        assertThat(result, samePropertyValuesAs(expected));
    }

}