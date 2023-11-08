package com.ajromero.webapp.web.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CheckoutConfirmDtoTest {

    private CheckoutConfirmDto checkoutConfirmDtoUnderTest;

    @BeforeEach
    void setUp() {
        checkoutConfirmDtoUnderTest = new CheckoutConfirmDto();
    }

    @Test
    void testIdGetterAndSetter() {
        final Long id = 0L;
        checkoutConfirmDtoUnderTest.setId(id);
        assertThat(checkoutConfirmDtoUnderTest.getId()).isEqualTo(id);
    }

    @Test
    void testSecurityCodeGetterAndSetter() {
        final String securityCode = "securityCode";
        checkoutConfirmDtoUnderTest.setSecurityCode(securityCode);
        assertThat(checkoutConfirmDtoUnderTest.getSecurityCode()).isEqualTo(securityCode);
    }
}
