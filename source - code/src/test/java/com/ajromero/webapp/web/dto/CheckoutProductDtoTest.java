package com.ajromero.webapp.web.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CheckoutProductDtoTest {

    private CheckoutProductDto checkoutProductDtoUnderTest;

    @BeforeEach
    void setUp() {
        checkoutProductDtoUnderTest = new CheckoutProductDto();
    }


    @Test
    void testCompareTo_ThrowsNullPointerException() {
        // Setup
        final CheckoutProductDto o = new CheckoutProductDto();
        o.setId(0L);
        o.setIdProduct(0L);
        o.setQuantity(0);
        o.setPrice(0.0);

        // Run the test
        assertThatThrownBy(() -> checkoutProductDtoUnderTest.compareTo(o)).isInstanceOf(NullPointerException.class);
    }




    @Test
    void testIdGetterAndSetter() {
        final Long id = 0L;
        checkoutProductDtoUnderTest.setId(id);
        assertThat(checkoutProductDtoUnderTest.getId()).isEqualTo(id);
    }

    @Test
    void testIdProductGetterAndSetter() {
        final Long idProduct = 0L;
        checkoutProductDtoUnderTest.setIdProduct(idProduct);
        assertThat(checkoutProductDtoUnderTest.getIdProduct()).isEqualTo(idProduct);
    }

    @Test
    void testQuantityGetterAndSetter() {
        final Integer quantity = 0;
        checkoutProductDtoUnderTest.setQuantity(quantity);
        assertThat(checkoutProductDtoUnderTest.getQuantity()).isEqualTo(quantity);
    }

    @Test
    void testPriceGetterAndSetter() {
        final Double price = 0.0;
        checkoutProductDtoUnderTest.setPrice(price);
        assertThat(checkoutProductDtoUnderTest.getPrice()).isEqualTo(price, within(0.0001));
    }
}
