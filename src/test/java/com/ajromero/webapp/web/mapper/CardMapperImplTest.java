package com.ajromero.webapp.web.mapper;

import com.ajromero.webapp.persistence.domain.CardPayment;
import com.ajromero.webapp.persistence.domain.Customer;
import com.ajromero.webapp.web.dto.CCheckoutDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardMapperImplTest {

    private CardMapperImpl cardMapperImplUnderTest;

    @BeforeEach
    void setUp() {
        cardMapperImplUnderTest = new CardMapperImpl();
    }

    @Test
    void testToDto() {
        // Setup
        final CardPayment card = new CardPayment();
        card.setId(0L);
        final Customer customer = new Customer();
        card.setCustomer(customer);
        card.setCardNumber("cardNumber");
        card.setCardHoldername("cardHoldername");
        card.setExpirationDate("expirationDate");

        // Run the test
        final CCheckoutDto result = cardMapperImplUnderTest.toDto(card);

        // Verify the results
    }

    @Test
    void testToEntity() {
        // Setup
        final CCheckoutDto dto = new CCheckoutDto();
        dto.setId(0L);
        dto.setCardNumber("cardNumber");
        dto.setCardHoldername("cardHoldername");
        dto.setExpirationDate("expirationDate");
        dto.setSecurityCode("securityCode");

        final CardPayment expectedResult = new CardPayment();
        expectedResult.setId(0L);
        final Customer customer = new Customer();
        expectedResult.setCustomer(customer);
        expectedResult.setCardNumber("cardNumber");
        expectedResult.setCardHoldername("cardHoldername");
        expectedResult.setExpirationDate("expirationDate");

        // Run the test
        final CardPayment result = cardMapperImplUnderTest.toEntity(dto);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
