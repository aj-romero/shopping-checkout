package com.ajromero.webapp.persistence.repositories;


import com.ajromero.webapp.persistence.domain.CustomerAddresses;
import com.ajromero.webapp.persistence.domain.Customers;
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
class ICustomerAddressesTest {

    @Autowired
    ICustomers customers;

    @Autowired
    ICustomerAddresses addresses;

    @Test
    void whenAddress_thenAssert() {
        Customers customer = new Customers();
        customer.setId("uiid-code-for-customer-from-keycloak");
        Customers expected = customers.save(customer);

        CustomerAddresses address = new CustomerAddresses();
        address.setCustomer(expected);

        CustomerAddresses newExpected = addresses.save(address);



        assertThat(address,is(equalTo(newExpected)));
    }

    @Test
    void whenNewAddressWithCustomer_thenAssert() {
        Customers customer = new Customers();
        customer.setId("uiid-code-for-customer-from-keycloak");
        Customers expected = customers.save(customer);



        CustomerAddresses address = new CustomerAddresses();
        //address.setCustomer(expected);
        address.setAddressName("Aunty House");
        expected.addAddress(address);
        customers.save(expected);



        assertThat(address.getCustomer(),is(equalTo(expected)));
    }
}

