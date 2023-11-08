package com.ajromero.webapp.payment.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

class GenerateUuidTest {

    private GenerateUuid generateUuidUnderTest;

    @BeforeEach
    void setUp() {
        generateUuidUnderTest = GenerateUuid.getInstance();
    }

    @Test
    void testGetInstance() {
        // Setup
        // Run the test
        final GenerateUuid result = GenerateUuid.getInstance();

        assertThat(result,is(notNullValue()));
    }

    @Test
    void testGenerateCode() {
       assertThat(this.generateUuidUnderTest.generateCode(),is(notNullValue()));
    }
}
