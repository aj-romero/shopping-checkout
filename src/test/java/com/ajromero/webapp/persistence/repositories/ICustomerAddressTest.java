package com.ajromero.webapp.persistence.repositories;


import com.ajromero.webapp.persistence.domain.CustomerAddress;
import com.ajromero.webapp.persistence.domain.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("Integration Customer Addresses Repository test")
class ICustomerAddressTest {

    @Autowired
    ICustomers customers;

    @Autowired
    ICustomerAddresses addresses;

    @Test
    void whenAddress_thenAssert() {
        Customer customer = new Customer();
        customer.setId("uiid-code-for-customer-from-keycloak");
        Customer expected = customers.save(customer);

        CustomerAddress address = new CustomerAddress();
        address.setAddressName("My house address");
        address.setCustomer(expected);

        CustomerAddress newExpected = addresses.save(address);



        assertThat(address,is(equalTo(newExpected)));
    }

    @Test
    void whenNewAddressWithCustomer_thenAssert() {
        Customer customer = new Customer();
        customer.setId("uiid-code-for-customer-from-keycloak");
        Customer expected = customers.save(customer);



        CustomerAddress address = new CustomerAddress();
        //address.setCustomer(expected);
        address.setAddressName("Aunty House");
        expected.addAddress(address);
        customers.save(expected);



        assertThat(address.getCustomer(),is(equalTo(expected)));
    }
}

