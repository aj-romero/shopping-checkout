package com.ajromero.webapp.service;

import com.ajromero.webapp.persistence.domain.Checkout;
import com.ajromero.webapp.persistence.domain.CheckoutProduct;
import com.ajromero.webapp.persistence.domain.Product;
import com.ajromero.webapp.persistence.repositories.ICheckoutRepository;
import com.ajromero.webapp.web.dto.CheckoutBasicDto;
import com.ajromero.webapp.web.dto.CheckoutProductDto;
import com.ajromero.webapp.web.dto.CheckoutWithShippingDto;
import com.ajromero.webapp.web.dto.ShippingDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Mockito.*;

@DisplayName("Checkout Service Test")
class CheckoutServiceTest {

    @Mock
    CheckoutServiceFacade facade;


    @Mock
    ICheckoutRepository checkouts;

    @InjectMocks
    CheckoutService checkoutService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Save basic checkout")
    void whenCreatedBasicCheckout_thenAssert() {
        CheckoutBasicDto basicDto = new CheckoutBasicDto();
        Checkout checkout = new Checkout();

        when(facade.basicRespCheckout(any())).thenReturn(checkout);
        when(checkouts.save(any())).thenReturn(checkout);
        when(facade.toDto(any())).thenReturn(basicDto);

        CheckoutBasicDto result = checkoutService.saveCheckoutBasic(basicDto);

        verify(checkouts).save(any());

        assertThat(result,is(equalTo(basicDto)));
    }

    @Test
    @DisplayName("Test add product to checkout test")
    void whenAddProductToCheckout_thenAssert() {
        Product product = new Product();
        product.setId(1l);
        CheckoutProduct ckproduct = new CheckoutProduct();
        ckproduct.setProduct(product);
        Checkout checkout = new Checkout();
        CheckoutBasicDto expected = new CheckoutBasicDto();
        CheckoutProductDto input = new CheckoutProductDto();

        when(facade.addProduct(1l,input)).thenReturn(ckproduct);
        when(checkouts.findById(anyLong())).thenReturn(Optional.of(checkout));
        //checkout.addDetail(product);
        when(checkouts.save(checkout)).thenReturn(checkout);
        when(facade.toDto(checkout)).thenReturn(expected);

        CheckoutBasicDto result = checkoutService.addProduct(1L,input );

        verify(checkouts).save(any());
        assertThat(result,is(equalTo(expected)));
    }

    @Test
    @DisplayName("Test add quantity to product test")
    void whenAddQuantitytoCheckoutProduct_thenAssert() {

        CheckoutProductDto productDto = new CheckoutProductDto();
        productDto.setIdProduct(1L);
        CheckoutBasicDto checkout = new CheckoutBasicDto();
        CheckoutBasicDto expected = new CheckoutBasicDto();

        when(facade.updateProductQuantity(1L,productDto)).thenReturn(expected);

        CheckoutBasicDto result = checkoutService.updateQuantityProduct(1L,productDto);

        verify(facade).updateProductQuantity(anyLong(),any());
        assertThat(result,is(equalTo(expected)));

    }

    @Test
    void whenDeleteCheckoutProduct_thenAssert() {
        Product product = new Product();
        product.setId(1l);
        CheckoutProduct ckproduct = new CheckoutProduct();
        ckproduct.setProduct(product);
        Checkout checkout = new Checkout();
        checkout.addDetail(ckproduct);
        CheckoutBasicDto expected = new CheckoutBasicDto();

        when(facade.removeCheckoutProduct(anyLong(),any())).thenReturn(checkout);

        checkoutService.deleteCheckoutProduct(1l, 1l);

        verify(facade).removeCheckoutProduct(anyLong(),anyLong());
    }

    @Test
    void whenAddShippingAddressToCheckout_thenAssert() {
        CheckoutWithShippingDto ckWShippingAddress = new CheckoutWithShippingDto();
        when(facade.saveShippingAddress(anyLong(),any())).thenReturn(ckWShippingAddress);

        CheckoutWithShippingDto result = checkoutService.saveShippingAddress(1L, new ShippingDto());

        assertThat(result,is(equalTo(ckWShippingAddress)));

    }

}