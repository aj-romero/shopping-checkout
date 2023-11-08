package com.ajromero.webapp.service;

import com.ajromero.webapp.persistence.domain.Customer;
import com.ajromero.webapp.persistence.domain.CustomerAddress;
import com.ajromero.webapp.persistence.repositories.ICustomerAddressRepository;
import com.ajromero.webapp.persistence.repositories.ICustomerRepository;
import com.ajromero.webapp.web.dto.AddressDto;
import com.ajromero.webapp.web.mapper.AddressMapper;
import com.ajromero.webapp.web.validation.IVerifyContent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Mockito.*;

@DisplayName("Address Service Test")
class AddressServiceTest {

    @Mock
    ICustomerRepository customers;

    @Mock
    IVerifyContent verify;

    @Mock
    ICustomerAddressRepository addresses;

    @Mock
    AddressMapper addressMapper;

    @Mock
    SecurityContext securityContext;

    @InjectMocks
    AddressService addressService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    String getUserAuth() {
        String idUser = "";
        Authentication authMock = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authMock);
        SecurityContextHolder.setContext(securityContext);
        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn(idUser);
        return idUser;
    }

    @Test
    void whenListAddressesByCustomer_thenAssert() {
        String userId = getUserAuth();
        Customer user = new Customer();
        AddressDto dtoAddress = new AddressDto();
        List<AddressDto> listAddress = new ArrayList<>();
        List<CustomerAddress> listCAddress = new ArrayList<>();

        when(customers.findById(anyString())).thenReturn(Optional.of(user));
        when(addresses.findByCustomer(any())).thenReturn(listCAddress);
        when(addressMapper.toDto(any())).thenReturn(dtoAddress);
        doNothing().when(verify).verifyContent(anyBoolean(),anyString());

        List<AddressDto> result = addressService.findAll();
        List<AddressDto> expected = listCAddress.stream().map(addressMapper::toDto).toList();

        verify(addresses).findByCustomer(any());
        assertThat(result,is(equalTo(expected)));
    }

    @Test
    void whenSaveNewAddress_thenAssert() {
        String userId = getUserAuth();
        Customer user = new Customer();
        CustomerAddress address = new CustomerAddress();
        AddressDto resource = new AddressDto();

        when(customers.findById(anyString())).thenReturn(Optional.of(user));
        doNothing().when(verify).verifyContent(anyBoolean(),anyString());
        when(addressMapper.toEntity(any())).thenReturn(address);
        when(addresses.save(any())).thenReturn(address);

        AddressDto result = addressService.save(resource);
        AddressDto expected = addressMapper.toDto(address);

        verify(addresses).save(any());
        assertThat(result,is(equalTo(expected)));

    }

    @Test
    void whenUpdateAddress_thenAssert() {
        Long id = 1l;
        AddressDto resource = new AddressDto();
        AddressDto expected = new AddressDto();

        CustomerAddress address = new CustomerAddress();
        Customer customer = new Customer();
        Optional<Customer> customerOptional = Optional.of(customer);
        String userId = getUserAuth();
        when(addresses.findById(anyLong())).thenReturn(Optional.of(address));
        when(customers.findById(userId)).thenReturn(customerOptional);
        when(addressMapper.toEntity(any())).thenReturn(address);
        when(addressMapper.toDto(any())).thenReturn(expected);


        when(addresses.save(any())).thenReturn(address);

        AddressDto result = addressService.update(id,resource);
        assertThat(result,is(equalTo(expected)));

    }

}