package com.ajromero.webapp.web.mapper;

import com.ajromero.webapp.persistence.domain.Customer;
import com.ajromero.webapp.web.dto.CustomerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerMapperImplTest {

    private CustomerMapperImpl customerMapperImplUnderTest;

    @BeforeEach
    void setUp() {
        customerMapperImplUnderTest = new CustomerMapperImpl();
    }

    @Test
    void testToDto() {
        // Setup
        final Customer customer = new Customer("id", "firstName", "lastName", "email", "phone");

        // Run the test
        final CustomerDto result = customerMapperImplUnderTest.toDto(customer);

        // Verify the results
    }

    @Test
    void testToEntity() {
        // Setup
        final CustomerDto customerDto = new CustomerDto();
        customerDto.setId("id");
        customerDto.setFirstName("firstName");
        customerDto.setLastName("lastName");
        customerDto.setEmail("email");
        customerDto.setPhone("phone");

        final Customer expectedResult = new Customer("id", "firstName", "lastName", "email", "phone");

        // Run the test
        final Customer result = customerMapperImplUnderTest.toEntity(customerDto);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
