package com.ajromero.webapp.web.validation.checkout.order;

import com.ajromero.webapp.persistence.domain.Checkout;
import com.ajromero.webapp.persistence.domain.Customer;
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
class CustomerChainTest {

    @Mock
    private IVerifyContent mockVerifyContent;
    @Mock
    private ICheckoutChain<Checkout> mockValidator;

    private CustomerChain customerChainUnderTest;

    @BeforeEach
    void setUp() {
        customerChainUnderTest = new CustomerChain(mockVerifyContent);
        customerChainUnderTest.nextValidate(mockValidator);
    }

    @Test
    void testValidate() {
        // Setup
        final Checkout resource = new Checkout();
        final Customer customer = new Customer();
        customer.setFirstName("firstName");
        customer.setLastName("lastName");
        customer.setEmail("email");
        resource.setCustomer(customer);

        when(mockValidator.validate(any(Checkout.class))).thenReturn(false);

        // Run the test
        final Boolean result = customerChainUnderTest.validate(resource);

        // Verify the results
        assertThat(result).isFalse();
        //verify(mockVerifyContent).verifyBadRequest(true, "Customer email is required");
    }

    @Test
    void testValidate_ICheckoutChainReturnsTrue() {
        // Setup
        final Checkout resource = new Checkout();
        final Customer customer = new Customer();
        customer.setFirstName("firstName");
        customer.setLastName("lastName");
        customer.setEmail("email");
        resource.setCustomer(customer);

        when(mockValidator.validate(any(Checkout.class))).thenReturn(true);

        // Run the test
        final Boolean result = customerChainUnderTest.validate(resource);

        // Verify the results
        assertThat(result).isTrue();
        //verify(mockVerifyContent).verifyBadRequest(true, "Customer email is required");
    }
}
