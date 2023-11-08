package com.ajromero.webapp.service;

import com.ajromero.webapp.persistence.domain.Customer;
import com.ajromero.webapp.persistence.domain.Product;
import com.ajromero.webapp.persistence.repositories.ICustomerRepository;
import com.ajromero.webapp.web.dto.CustomerDto;
import com.ajromero.webapp.web.mapper.CustomerMapper;
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

import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Mockito.*;

@DisplayName("Customer Service Test")
class CustomerServiceTest {

    @Mock
    ICustomerRepository customers;

    @Mock
    CustomerMapper customerMapper;

    @Mock
    SecurityContext securityContext;

    @Mock
    IVerifyContent verify;

    @InjectMocks
    CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("Get Customer test")
    void whenFindCustomer_thenAssert() {
        Customer customer = new Customer();

        String idUser = getUserAuth();

        when(customers.findById(anyString())).thenReturn(Optional.of(customer));

        doNothing().when(verify).verifyContent(eq(false),anyString());

        CustomerDto result = customerService.getCustomer();
        CustomerDto expected = customerMapper.toDto(customer);

        verify(customers).findById(anyString());
        assertThat(result,is(equalTo(expected)));

    }

    @Test
    void whenSaveNewCustomer_thenAssert() {
        CustomerDto inCustomerDto = new CustomerDto();

        Customer newCustomer = new Customer();

        doNothing().when(verify).verifyEmail(any(),anyString());
        when(customerMapper.toEntity(any())).thenReturn(newCustomer);
        String idUser = getUserAuth();
        when(customers.save(any())).thenReturn(newCustomer);

        CustomerDto actual = customerService.save(inCustomerDto);
        CustomerDto expected = customerMapper.toDto(newCustomer);

        verify(customers).save(any());
        assertThat(actual,is(equalTo(expected)));

    }

    @Test
    void whenUpdateCustomer_thenAssert() {
        CustomerDto expected = new CustomerDto();
        Customer oldCustomer = new Customer();
        oldCustomer.setId("non-uuid-id");
        Optional<Customer> dbCustomer = Optional.of(oldCustomer);

        String idUser = getUserAuth();
        when(customers.findById(anyString())).thenReturn(dbCustomer);
        when(customerMapper.toDto(any())).thenReturn(expected);

        CustomerDto result = customerService.update(new CustomerDto());

        assertThat(result,is(equalTo(expected)));
        assertThat(expected.getFirstName(),is(equalTo(oldCustomer.getFirstName())));


    }

    String getUserAuth() {
        String idUser = "";
        Authentication authMock = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authMock);
        SecurityContextHolder.setContext(securityContext);
        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn(idUser);
        return idUser;
    }



}