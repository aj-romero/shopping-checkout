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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Save and Update Customer Address Controller Test")
class PersistAddressControllerTest {

    @Mock
    IAddressService addressService;

    @InjectMocks
    CustomerAddressController addressController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Save new Address success")
    void whenSaveCustomerAddress_thenAssert() {
        AddressDto dtoAddress = new AddressDto();
        when(addressService.save(any())).thenReturn(dtoAddress);

        ResponseEntity<AddressDto> result = addressController.saveAddress(dtoAddress);
        ResponseEntity<AddressDto> expected = ResponseEntity.
                status(HttpStatus.CREATED).body(dtoAddress);

        verify(addressService).save(any());
        assertThat(result, samePropertyValuesAs(expected));

    }

    @Test
    @DisplayName("Update address success")
    void whenUpdateCustomerAddress_thenAssert() {
        AddressDto dtoAddress = new AddressDto();
        when(addressService.update(anyLong(),any())).thenReturn(dtoAddress);

        ResponseEntity<AddressDto> result = addressController.updateAddress(1l,dtoAddress);
        ResponseEntity<AddressDto> expected = ResponseEntity.
                status(HttpStatus.OK).body(dtoAddress);

        verify(addressService).update(anyLong(),any());
        assertThat(result, samePropertyValuesAs(expected));
    }


}