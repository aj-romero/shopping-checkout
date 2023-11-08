package com.ajromero.webapp.web.validation.checkout.order;

import com.ajromero.webapp.persistence.domain.Checkout;
import com.ajromero.webapp.persistence.domain.CustomerAddress;
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
class AddressChainTest {

    @Mock
    private IVerifyContent mockVerifyContent;
    @Mock
    private ICheckoutChain<Checkout> mockValidator;

    private AddressChain addressChainUnderTest;

    @BeforeEach
    void setUp() {
        addressChainUnderTest = new AddressChain(mockVerifyContent);
        addressChainUnderTest.nextValidate(mockValidator);
    }

    @Test
    void testValidate() {
        // Setup
        final Checkout resource = new Checkout();
        //resource.setPaymentProcessor(null);
        resource.setId(0L);
        resource.setPaymentCode("paymentCode");
        resource.setStatus(Checkout.Status.OPEN);
        final CustomerAddress shippingAddress = new CustomerAddress();
        resource.setShippingAddress(shippingAddress);

        when(mockValidator.validate(any(Checkout.class))).thenReturn(false);

        // Run the test
        final Boolean result = addressChainUnderTest.validate(resource);

        // Verify the results
        assertThat(result).isFalse();
        //verify(mockVerifyContent).verifyBadRequest(true, "Shipping Address was not added");
    }

    @Test
    void testValidate_ICheckoutChainReturnsTrue() {
        // Setup
        final Checkout resource = new Checkout();
       // resource.setPaymentProcessor(null);
        resource.setId(0L);
        resource.setPaymentCode("paymentCode");
        resource.setStatus(Checkout.Status.OPEN);
        final CustomerAddress shippingAddress = new CustomerAddress();
        resource.setShippingAddress(shippingAddress);

        when(mockValidator.validate(any(Checkout.class))).thenReturn(true);

        // Run the test
        final Boolean result = addressChainUnderTest.validate(resource);

        // Verify the results
        assertThat(result).isTrue();
        //verify(mockVerifyContent).verifyBadRequest(true, "Shipping Address was not added");
    }
}
