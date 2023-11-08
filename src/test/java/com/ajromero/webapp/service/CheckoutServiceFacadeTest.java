package com.ajromero.webapp.service;

import com.ajromero.webapp.persistence.domain.*;
import com.ajromero.webapp.persistence.repositories.*;
import com.ajromero.webapp.web.dto.*;
import com.ajromero.webapp.web.mapper.*;
import com.ajromero.webapp.web.validation.IVerifyContent;
import com.ajromero.webapp.web.validation.checkout.CheckoutValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CheckoutServiceFacadeTest {

    @Mock
    private ICustomerRepository mockCustomers;
    @Mock
    private CheckoutBasicMapper mockCkMapper;

    @Mock
    SecurityContext securityContext;

    @Mock
    private CardMapper mockCardMapper;
    @Mock
    private CheckoutValidator mockCkValidator;

    @InjectMocks
    private CheckoutServiceFacade checkoutServiceFacadeUnderTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBasicRespCheckout_returnFalse() {
        // Setup
        final CheckoutProductDto productDto = new CheckoutProductDto();
        productDto.setId(1L);
        productDto.setIdProduct(1L);
        final CheckoutBasicDto resource = new CheckoutBasicDto(Set.of(productDto));
        when(mockCkValidator.validate(any())).thenReturn(false);

        // Configure CheckoutBasicMapper.toEntity(...).
        final Checkout checkout = new Checkout();
        final CheckoutProduct product = new CheckoutProduct();
        final Product product1 = new Product();
        product1.setId(0L);
        product.setProduct(product1);
        checkout.setProducts(Set.of(product));
        when(mockCkMapper.toEntity(any())).thenReturn(checkout);

        // Run the test
        final Checkout result = checkoutServiceFacadeUnderTest.basicRespCheckout(resource);

        // Verify the results
        assertThat(result,is(equalTo(checkout)));

    }

    @Test
    void testBasicRespCheckout_thenAssert() {
        // Setup
        final CheckoutProductDto productDto = new CheckoutProductDto();
        productDto.setId(1L);
        productDto.setIdProduct(1L);
        final CheckoutBasicDto resource = new CheckoutBasicDto(Set.of(productDto));
        when(mockCkValidator.validate(any())).thenReturn(true);

        // Configure CheckoutBasicMapper.toEntity(...).
        final Checkout checkout = new Checkout();
        final CheckoutProduct product = new CheckoutProduct();
        final Product product1 = new Product();
        final Customer customer = new Customer();
        customer.setId(this.getUserAuth());
        product1.setId(0L);
        product.setProduct(product1);
        checkout.setProducts(Set.of(product));
        checkout.setCustomer(customer);
        when(mockCkMapper.toEntity(any())).thenReturn(checkout);
        when(mockCustomers.findById(anyString())).thenReturn(Optional.of(customer));

        // Run the test
        final Checkout result = checkoutServiceFacadeUnderTest.basicRespCheckout(resource);

        // Verify the results
        assertThat(result,is(equalTo(checkout)));

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
