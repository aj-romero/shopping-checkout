package com.ajromero.webapp.persistence.repositories;

import com.ajromero.ShoppingApplication;
import com.ajromero.webapp.persistence.domain.Products;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("Integration User Repository test")
class IProductsTest {

    @Autowired
    private IProducts products;

    @Test
    void whenNewProduct_thenSuccess() {
        Products product = new Products("101","Mouse",100,11.45);
       // product.setId(null);

        Products expect = products.save(product);

        assertThat(product,is(equalTo(expect)));
    }
}

