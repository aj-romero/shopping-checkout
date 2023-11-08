package com.ajromero.webapp.web.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ApiErrorTest {

    @Mock
    private Map<String, String> mockErrors;

    private ApiError apiErrorUnderTest;

    @BeforeEach
    void setUp() {
        apiErrorUnderTest = new ApiError(HttpStatus.OK, "message", mockErrors);
    }

    @Test
    void testStatusGetterAndSetter() {
        final HttpStatus status = HttpStatus.OK;
        apiErrorUnderTest.setStatus(status);
        assertThat(apiErrorUnderTest.getStatus()).isEqualTo(status);
    }

    @Test
    void testMessageGetterAndSetter() {
        final String message = "message";
        apiErrorUnderTest.setMessage(message);
        assertThat(apiErrorUnderTest.getMessage()).isEqualTo(message);
    }

    @Test
    void testGetErrors() {
        // Setup
        final Map<String, String> expectedResult = Map.ofEntries(Map.entry("value", "value"));
        apiErrorUnderTest.setErrors(Map.ofEntries(Map.entry("value", "value")));

        // Run the test
        final Map<String, String> result = apiErrorUnderTest.getErrors();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSetErrors() {
        final Map<String, String> errors = Map.ofEntries(Map.entry("value", "value"));
        apiErrorUnderTest.setErrors(errors);
    }
}
