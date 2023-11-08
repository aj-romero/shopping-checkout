package com.ajromero.webapp.web.controller;

import com.ajromero.webapp.service.interfaces.ICustomerService;
import com.ajromero.webapp.web.dto.CustomerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.mockito.Mockito.*;

class PersistCustomerControllerTest {

    @Mock
    ICustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenSaveCustomer_thenAssert() {
        CustomerDto customerDto = new CustomerDto();
        when(customerService.save(any())).thenReturn(customerDto);

        ResponseEntity<CustomerDto> result = customerController.saveCustomer(customerDto);
        ResponseEntity<CustomerDto> expected = ResponseEntity.
                status(HttpStatus.CREATED).body(customerDto);

        verify(customerService).save(any());
        assertThat(result, samePropertyValuesAs(expected));
    }

    @Test
    void updatecustomerSuccess_thenAssert() {
        CustomerDto customerDto = new CustomerDto();
        when(customerService.update(any())).thenReturn(customerDto);

        ResponseEntity<CustomerDto> result = customerController.updateCustomer(customerDto);
        ResponseEntity<CustomerDto> expected = ResponseEntity.
                status(HttpStatus.OK).body(customerDto);

        verify(customerService).update(any());
        assertThat(result, samePropertyValuesAs(expected));
    }

}