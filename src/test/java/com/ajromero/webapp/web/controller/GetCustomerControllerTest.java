package com.ajromero.webapp.web.controller;

import com.ajromero.webapp.service.interfaces.ICustomerService;
import com.ajromero.webapp.web.dto.CustomerDto;
import com.ajromero.webapp.web.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;

@DisplayName("Customer Controller test")
class GetCustomerControllerTest {

    @Mock
    ICustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Get Customer Success")
    void getCustomerSuccess_thenAssert() {
        CustomerDto dtoCustomer = new CustomerDto();
        when(customerService.getCustomer()).thenReturn(dtoCustomer);

        ResponseEntity<CustomerDto> result = customerController.getCustomer();
        ResponseEntity<CustomerDto> expected = ResponseEntity.
                status(HttpStatus.OK).body(dtoCustomer);

        verify(customerService).getCustomer();
        assertThat(result, samePropertyValuesAs(expected));
    }

    @Test
    @DisplayName("Fail get user")
    void whenFindByCustomer_thenThrowException() {
        when(customerService.getCustomer()).thenThrow(ResourceNotFoundException.class);

        final ResourceNotFoundException exception =
                Assertions.assertThrows(ResourceNotFoundException.class,
                ()-> {
                customerController.getCustomer();
                });

        verify(customerService).getCustomer();
        assertThat(exception.getClass(),is(equalTo(ResourceNotFoundException.class)));
    }

}