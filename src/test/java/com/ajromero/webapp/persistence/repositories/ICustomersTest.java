package com.ajromero.webapp.persistence.repositories;

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
@DisplayName("Integration Customers Repository test")
class ICustomersTest {

    @Autowired
    ICustomers customers;

    @Test
    void whenNewCustomer_thenAssert() {
        Customers customer = new Customers();
        customer.setId("uiid-code-for-customer-from-keycloak");

        Customers expected = customers.save(customer);

        assertThat(customer,is(equalTo(expected)));
    }

}