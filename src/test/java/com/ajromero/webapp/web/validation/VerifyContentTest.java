package com.ajromero.webapp.web.validation;

import com.ajromero.webapp.persistence.repositories.ICustomerRepository;
import com.ajromero.webapp.web.exception.EmailResourceException;
import com.ajromero.webapp.web.exception.MyBadRequestException;
import com.ajromero.webapp.web.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

class VerifyContentTest {

    private VerifyContent verifyContentUnderTest;

    @BeforeEach
    void setUp() {
        verifyContentUnderTest = new VerifyContent();
    }

    @Test
    void testVerifyContent() {
        ResourceNotFoundException exception = Assertions
                .assertThrows(ResourceNotFoundException.class,
                        () -> {
                                verifyContentUnderTest.verifyContent(true, "msj");
                        });
        verifyContentUnderTest.verifyContent(false, "msj");

        assertThat(exception.getClass(),is(equalTo(ResourceNotFoundException.class)));

    }

    @Test
    void testVerifyEmail() {
        // Setup
        final ICustomerRepository customers = mock(ICustomerRepository.class);
        when(customers.existsByEmail(any())).thenReturn(true);

        // Run the test
        EmailResourceException exception = Assertions.assertThrows(EmailResourceException.class,
                () -> {
                    verifyContentUnderTest.verifyEmail(customers, "email");
                }
                );
        assertThat(exception.getClass(),is(equalTo(EmailResourceException.class)));
        // Verify the results
    }

    @Test
    void testVerifyBadRequest() {

        MyBadRequestException excep = Assertions.assertThrows(MyBadRequestException.class,
                ()-> {
                    verifyContentUnderTest.verifyBadRequest(true, "msj");
                });
        assertThat(excep.getClass(),is(equalTo(MyBadRequestException.class)));

    }
}
