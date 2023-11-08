package com.ajromero.webapp.persistence.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

@DisplayName("Customer entity Test")
class CustomerTest {

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
    }

    @Test
    void setUpCustomer_thenAssert() {
        String id = "this-is-id_uuid";
        String firstName = "Alber";
        String lastName = "Romero";
        String email = "alber.romero@kodigo.org";
        String phone = "+503 3345 3321";

        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setId(id);

        assertThat(customer.getPhone(),is(equalTo(phone)));
        assertThat(customer.getFirstName(),is(equalTo(firstName)));
        assertThat(customer.getLastName(), is(equalTo(lastName)));
        assertThat(customer.getEmail(),is(equalTo(email)));
        assertThat(customer.getId(),is(equalTo(id)));

        assertThat(customer.toString(),is(notNullValue()));
    }

    @Test
    void equalAndHascode_thenAssert() {
        customer = new Customer("id-uuid","Alber",
                "Romero","ajromero@gmail.com",null);
        Customer otherObject = new Customer("id-uuid","Alber",
                "Romero","ajromero@gmail.com",null);

        Customer nonIqual = new Customer();
        nonIqual.setId("non-iqual-uuid");

        assertThat(customer.equals(otherObject),is(true));
        assertThat(customer.hashCode(),is(equalTo(otherObject.hashCode())));
        assertThat(customer.equals(null),is(false));

        assertThat(customer.equals(nonIqual),is(false));
        assertThat(customer.hashCode(),not(nonIqual.hashCode()));

    }

    @Test
    void addCustomerAddress_thenAssert() {
        CustomerAddress address = new CustomerAddress();
        address.setAddressName("Mi new address");

        customer.addAddress(address);

        assertThat(customer.getAddresses(),is(notNullValue()));
    }

    @Test
    void addCustomerPaymentMethod_thenAssert() {
        CardPayment card = new CardPayment();
        card.setCardNumber("4000343221214567");

        customer.addCardPayment(card);

        assertThat(customer.getCards(),is(notNullValue()));
    }

}