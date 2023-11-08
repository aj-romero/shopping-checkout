package com.ajromero.webapp.web.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

class EmailResourceExceptionTest {

    @Test
    void whenEmailResourceExceptionIsCalled_thenAssert() {
        EmailResourceException exception = new EmailResourceException("This call by handled error");

        assertThat(exception,is(notNullValue()));

    }
}
