package com.ajromero.webapp.web.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CheckoutPaymentDtoTest {

    private CheckoutPaymentDto checkoutPaymentDtoUnderTest;

    @BeforeEach
    void setUp() {
        checkoutPaymentDtoUnderTest = new CheckoutPaymentDto();
    }

    @Test
    void testIdGetterAndSetter() {
        final Long id = 0L;
        checkoutPaymentDtoUnderTest.setId(id);
        assertThat(checkoutPaymentDtoUnderTest.getId()).isEqualTo(id);
    }

    @Test
    void testIdCardGetterAndSetter() {
        final Long idCard = 0L;
        checkoutPaymentDtoUnderTest.setIdCard(idCard);
        assertThat(checkoutPaymentDtoUnderTest.getIdCard()).isEqualTo(idCard);
    }
}
