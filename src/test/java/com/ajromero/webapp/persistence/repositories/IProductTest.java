package com.ajromero.webapp.persistence.repositories;

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
@DisplayName("Integration Products Repository test")
class IProductTest {

    @Autowired
    private IProductRepository products;

    @Test
    void whenNewProduct_thenSuccess() {
        Product product = new Product();
        product.setName("USB 32GB");
        product.setCode("101");
        product.setStock(100);
        product.setPrice(4.75);

        Product expect = products.save(product);

        assertThat(product,is(equalTo(expect)));
    }
}

