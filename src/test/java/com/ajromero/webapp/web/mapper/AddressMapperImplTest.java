package com.ajromero.webapp.web.mapper;

import com.ajromero.webapp.persistence.domain.CustomerAddress;
import com.ajromero.webapp.web.dto.AddressDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AddressMapperImplTest {

    private AddressMapperImpl addressMapperImplUnderTest;

    @BeforeEach
    void setUp() {
        addressMapperImplUnderTest = new AddressMapperImpl();
    }

    @Test
    void testToDto() {
        // Setup
        final CustomerAddress address = new CustomerAddress();
        address.setId(0L);
        address.setAddressName("addressName");
        address.setState("state");
        address.setCity("city");
        address.setStreet("street");
        address.setReferencePhone("referencePhone");
        address.setHouseNumber("houseNumber");
        address.setReferenceName("referenceName");

        // Run the test
        final AddressDto result = addressMapperImplUnderTest.toDto(address);

        // Verify the results
    }

    @Test
    void testToEntity() {
        // Setup
        final AddressDto address = new AddressDto();
        address.setId(0L);
        address.setAddressName("addressName");
        address.setState("state");
        address.setCity("city");
        address.setStreet("street");
        address.setReferencePhone("referencePhone");
        address.setHouseNumber("houseNumber");
        address.setReferenceName("referenceName");

        final CustomerAddress expectedResult = new CustomerAddress();
        expectedResult.setId(0L);
        expectedResult.setAddressName("addressName");
        expectedResult.setState("state");
        expectedResult.setCity("city");
        expectedResult.setStreet("street");
        expectedResult.setReferencePhone("referencePhone");
        expectedResult.setHouseNumber("houseNumber");
        expectedResult.setReferenceName("referenceName");

        // Run the test
        final CustomerAddress result = addressMapperImplUnderTest.toEntity(address);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
