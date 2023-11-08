package com.ajromero.webapp.web.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ShippingDtoTest {

    private ShippingDto shippingDtoUnderTest;

    @BeforeEach
    void setUp() {
        shippingDtoUnderTest = new ShippingDto();
    }

    @Test
    void testIdGetterAndSetter() {
        final Long id = 0L;
        shippingDtoUnderTest.setId(id);
        assertThat(shippingDtoUnderTest.getId()).isEqualTo(id);
    }

    @Test
    void testIdShippingGetterAndSetter() {
        final Long idShipping = 0L;
        shippingDtoUnderTest.setIdShipping(idShipping);
        assertThat(shippingDtoUnderTest.getIdShipping()).isEqualTo(idShipping);
    }
}
