package com.ajromero.webapp.persistence.repositories;

import com.ajromero.webapp.persistence.domain.CheckoutProduct;
import com.ajromero.webapp.persistence.domain.Checkout;
import com.ajromero.webapp.persistence.domain.Customer;
import com.ajromero.webapp.persistence.domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("Integration Checkout Repository test")
class ICheckoutTest {
    @Autowired
    ICustomers customers;

    @Autowired
    IProducts products;

    @Autowired
    ICheckout checkouts;

    @Test
    void whenNewCheckoutSimple_thenAssert() {
        Customer customer = new Customer();
        customer.setId("uiid-code-for-customer-from-keycloak");
        Customer expected = customers.save(customer);

        Checkout checkout = Checkout.builder().build();
        checkout.setStatus(Checkout.Status.OPEN);
        checkout.setCustomer(expected);

        System.out.println(checkout.getCreatedAt());

        Checkout checkExpected = checkouts.save(checkout);
        assertThat(checkout,is(equalTo(checkExpected)));
    }

    @Test
    void wheCheckoutWithProduct_thenAssert() {
        //este es el escenario propuesto en los requerimientos crear un checkout solo con un customer y un producto
        Product product = new Product("101","Mouse",100,11.45);
        Product newProduct = products.save(product);

        Customer customer = new Customer();
        customer.setId("uiid-code-for-customer-from-keycloak");
        Customer newCustomer = customers.save(customer);

        Checkout checkout = Checkout.builder().build();
        checkout.setCustomer(newCustomer);

        CheckoutProduct detail = new CheckoutProduct();
        detail.setProduct(newProduct);
        detail.setQuantity(10);
        detail.setPrice(11.45);

        checkout.addDetail(detail);

        Checkout expected = checkouts.save(checkout);

        assertThat(checkout, is(equalTo(expected)));


    }
}
