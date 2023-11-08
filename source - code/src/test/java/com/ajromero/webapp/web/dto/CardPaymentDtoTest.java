package com.ajromero.webapp.web.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardPaymentDtoTest {

    private CardPaymentDto cardPaymentDtoUnderTest;

    @BeforeEach
    void setUp() {
        cardPaymentDtoUnderTest = new CardPaymentDto();
        cardPaymentDtoUnderTest.setCardNumber("4233789056438900");
    }

    @Test
    void testGetCardNumber() {
        int cardLen = cardPaymentDtoUnderTest.getCardNumber().length();
        String expected = "**"+cardPaymentDtoUnderTest.getCardNumber().substring(cardLen-4);
        // Run the test
        final String result = cardPaymentDtoUnderTest.getCardNumber();

        // Verify the results
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testIdGetterAndSetter() {
        final Long id = 0L;
        cardPaymentDtoUnderTest.setId(id);
        assertThat(cardPaymentDtoUnderTest.getId()).isEqualTo(id);
    }

    @Test
    void testCardHoldernameGetterAndSetter() {
        final String cardHoldername = "cardHoldername";
        cardPaymentDtoUnderTest.setCardHoldername(cardHoldername);
        assertThat(cardPaymentDtoUnderTest.getCardHoldername()).isEqualTo(cardHoldername);
    }

    @Test
    void testExpirationDateGetterAndSetter() {
        final String expirationDate = "expirationDate";
        cardPaymentDtoUnderTest.setExpirationDate(expirationDate);
        assertThat(cardPaymentDtoUnderTest.getExpirationDate()).isEqualTo(expirationDate);
    }
}
