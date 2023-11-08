package com.ajromero.webapp.web.controller;

import com.ajromero.webapp.service.interfaces.ICheckoutService;
import com.ajromero.webapp.web.dto.CheckoutPaymentDto;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Save Payment Method - Checkout Controller test")
class SavePaymentMethodCheckoutTest {
    @Mock
    ICheckoutService checkoutService;

    @InjectMocks
    CheckoutController checkoutController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Save Checkout success")
    void whenSavePaymentMethodCheckout_thenAssert() {
        String bodyString = " ";
        CheckoutPaymentDto dto = new CheckoutPaymentDto();

        when(checkoutService.savePaymentMethod(anyLong(),any())).thenReturn(bodyString);

        ResponseEntity<String> result = checkoutController.savePaymentMethod(1l,dto);
        ResponseEntity<String> expected = ResponseEntity.
                status(HttpStatus.CREATED).body(bodyString);

        verify(checkoutService).savePaymentMethod(anyLong(),any());
        assertThat(result, samePropertyValuesAs(expected));
    }

}