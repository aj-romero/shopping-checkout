package com.ajromero.webapp.web.controller;

import com.ajromero.webapp.service.interfaces.IAddressService;
import com.ajromero.webapp.web.dto.AddressDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Get Address Controller Test")
class GetAddressControllerTest {

    @Mock
    IAddressService addressService;

    @InjectMocks
    CustomerAddressController addressController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Get all addresses success")
    void whenGetCustomerAddress_thenAssert() {
        List<AddressDto> listAddress = new ArrayList<>();
        when(addressService.findAll()).thenReturn(listAddress);

        ResponseEntity<List<AddressDto>> result = addressController.getCustomerAddresses();
        ResponseEntity<List<AddressDto>> expected = ResponseEntity.
                status(HttpStatus.OK).body(listAddress);

        verify(addressService).findAll();
        assertThat(result, samePropertyValuesAs(expected));
    }

}