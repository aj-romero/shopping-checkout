package com.ajromero.webapp.web.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

class ResourceNotFoundExceptionTest {

    @Test
    void whenResourceNotFoundExceptionIsCalled_thenAssert() {
        ResourceNotFoundException exception = new ResourceNotFoundException("Error");

        assertThat(exception,is(notNullValue()));
    }

}