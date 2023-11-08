package com.ajromero.webapp.web.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerDtoTest {

    private CustomerDto customerDtoUnderTest;

    @BeforeEach
    void setUp() {
        customerDtoUnderTest = new CustomerDto();
    }

    @Test
    void testIdGetterAndSetter() {
        final String id = "id";
        customerDtoUnderTest.setId(id);
        assertThat(customerDtoUnderTest.getId()).isEqualTo(id);
    }

    @Test
    void testFirstNameGetterAndSetter() {
        final String firstName = "firstName";
        customerDtoUnderTest.setFirstName(firstName);
        assertThat(customerDtoUnderTest.getFirstName()).isEqualTo(firstName);
    }

    @Test
    void testLastNameGetterAndSetter() {
        final String lastName = "lastName";
        customerDtoUnderTest.setLastName(lastName);
        assertThat(customerDtoUnderTest.getLastName()).isEqualTo(lastName);
    }

    @Test
    void testEmailGetterAndSetter() {
        final String email = "email";
        customerDtoUnderTest.setEmail(email);
        assertThat(customerDtoUnderTest.getEmail()).isEqualTo(email);
    }

    @Test
    void testPhoneGetterAndSetter() {
        final String phone = "phone";
        customerDtoUnderTest.setPhone(phone);
        assertThat(customerDtoUnderTest.getPhone()).isEqualTo(phone);
    }
}
