package com.ajromero.webapp.web.dto;

import com.ajromero.webapp.persistence.domain.Checkout;
import com.ajromero.webapp.persistence.domain.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CheckoutInfoDtoTest {

    @Mock
    private Customer mockCustomer;
    @Mock
    private CardPaymentDto mockCardPayment;

    private CheckoutInfoDto checkoutInfoDtoUnderTest;

    @BeforeEach
    void setUp() {
        checkoutInfoDtoUnderTest = new CheckoutInfoDto();
        checkoutInfoDtoUnderTest.setCustomer(mockCustomer);
        checkoutInfoDtoUnderTest.setCardPayment(mockCardPayment);
    }

    @Test
    void testPaymentCodeGetterAndSetter() {
        final String paymentCode = "paymentCode";
        checkoutInfoDtoUnderTest.setPaymentCode(paymentCode);
        assertThat(checkoutInfoDtoUnderTest.getPaymentCode()).isEqualTo(paymentCode);
    }

    @Test
    void testStatusGetterAndSetter() {
        final Checkout.Status status = Checkout.Status.OPEN;
        checkoutInfoDtoUnderTest.setStatus(status);
        assertThat(checkoutInfoDtoUnderTest.getStatus()).isEqualTo(status);
    }

    @Test
    void testCreatedAtGetterAndSetter() {
        final Date createdAt = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        checkoutInfoDtoUnderTest.setCreatedAt(createdAt);
        assertThat(checkoutInfoDtoUnderTest.getCreatedAt()).isEqualTo(createdAt);
    }

    @Test
    void testUpdatedAtGetterAndSetter() {
        final Date updatedAt = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        checkoutInfoDtoUnderTest.setUpdatedAt(updatedAt);
        assertThat(checkoutInfoDtoUnderTest.getUpdatedAt()).isEqualTo(updatedAt);
    }

    @Test
    void testGetCustomer() {
        assertThat(checkoutInfoDtoUnderTest.getCustomer()).isEqualTo(mockCustomer);
    }

    @Test
    void testGetCardPayment() {
        assertThat(checkoutInfoDtoUnderTest.getCardPayment()).isEqualTo(mockCardPayment);
    }
}
