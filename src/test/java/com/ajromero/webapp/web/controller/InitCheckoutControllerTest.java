package com.ajromero.webapp.web.controller;

import com.ajromero.webapp.service.interfaces.ICheckoutService;
import com.ajromero.webapp.web.dto.CheckoutBasicDto;
import com.ajromero.webapp.web.dto.CustomerDto;
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

@DisplayName("Basic Checkout Controller test")
class InitCheckoutControllerTest {

    @Mock
    ICheckoutService checkoutService;

    @InjectMocks
    CheckoutController checkoutController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Create New Basic Checkout success")
    void whenCreateNewCheckoutBasic_thenAssert() {
        CheckoutBasicDto dtoBasicCk = new CheckoutBasicDto();
        when(checkoutService.saveCheckoutBasic(any())).thenReturn(dtoBasicCk);

        ResponseEntity<CheckoutBasicDto> result = checkoutController.basicCheckout(dtoBasicCk);
        ResponseEntity<CheckoutBasicDto> expected = ResponseEntity.
                status(HttpStatus.CREATED).body(dtoBasicCk);

        verify(checkoutService).saveCheckoutBasic(any());
        assertThat(result, samePropertyValuesAs(expected));
    }

}