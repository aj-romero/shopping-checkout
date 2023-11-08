package com.ajromero.webapp.web.exception;

import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


class MyBadRequestExceptionTest {

    @Test
    void whenMyBadRequestExceptionisCalled_thenAssert() {
        MyBadRequestException exception = new MyBadRequestException("Bad Request handle error");

        assertThat(exception,is(notNullValue()));
    }

}
