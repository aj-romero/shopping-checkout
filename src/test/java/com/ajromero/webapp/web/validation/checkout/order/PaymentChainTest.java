package com.ajromero.webapp.web.validation.checkout.order;

import com.ajromero.webapp.persistence.domain.CardPayment;
import com.ajromero.webapp.persistence.domain.Checkout;
import com.ajromero.webapp.web.validation.IVerifyContent;
import com.ajromero.webapp.web.validation.checkout.ICheckoutChain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentChainTest {

    @Mock
    private IVerifyContent mockVerifyContent;
    @Mock
    private ICheckoutChain<Checkout> mockValidator;

    private PaymentChain paymentChainUnderTest;

    @BeforeEach
    void setUp() {
        paymentChainUnderTest = new PaymentChain(mockVerifyContent);
        paymentChainUnderTest.nextValidate(mockValidator);
    }

    @Test
    void testValidate() {
        // Setup
        final Checkout resource = new Checkout();
        resource.setId(0L);
        resource.setPaymentCode("paymentCode");
        resource.setStatus(Checkout.Status.OPEN);
        final CardPayment cardPayment = new CardPayment();
        resource.setCardPayment(cardPayment);

        when(mockValidator.validate(any(Checkout.class))).thenReturn(false);

        // Run the test
        final Boolean result = paymentChainUnderTest.validate(resource);

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testValidate_ICheckoutChainReturnsTrue() {
        // Setup
        final Checkout resource = new Checkout();
        resource.setId(0L);
        resource.setPaymentCode("paymentCode");
        resource.setStatus(Checkout.Status.OPEN);
        final CardPayment cardPayment = new CardPayment();
        resource.setCardPayment(cardPayment);

        when(mockValidator.validate(any(Checkout.class))).thenReturn(true);

        // Run the test
        final Boolean result = paymentChainUnderTest.validate(resource);

        // Verify the results
        assertThat(result).isTrue();
    }
}
