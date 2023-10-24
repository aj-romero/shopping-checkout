package com.ajromero.webapp.persistence.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

@DisplayName("Products entity test")
class ProductsTest {

    private Products product;

    @BeforeEach
    void setUp() {
        product = new Products();
    }

    @Test
    void whenNewProduct_thenSuccess() {
       Long id = 1L;
       String code = "101";
       String name = "Mouse";
       Integer stock = 100;
       Double price = 13.50d;

       product.setId(id);
       product.setCode(code);
       product.setName(name);
       product.setStock(stock);
       product.setPrice(price);

       assertAll(()-> assertThat(product.getId(),is(equalTo(id))),
               ()->assertThat(product.getCode(),is(equalTo(code))),
               ()->assertThat(product.getName(),is(equalTo(name))),
               ()->assertThat(product.getStock(),is(equalTo(stock))),
               ()->assertThat(product.getPrice(),is(equalTo(price)))
               );
    }

    @Test
    void whenProductisEqual() {
        product = new Products("101","Mouse",100,14.40);
        Products otherProduct = new Products("101","Mouse",100,14.40);

        assertThat(product,is(equalTo(otherProduct)));
        assertThat(product.hashCode(),is(equalTo(otherProduct.hashCode())));
        assertThat(product,not(equalTo(null)));
        assertFalse(product.equals(null));

    }

    @Test
    void whenProductsAreNotEquals_thenAssert() {
        product = new Products("101","Mouse",100,14.40);
        Products otherProduct = new Products("102","Mouse",100,14.40);

        assertThat(product,not(equalTo(otherProduct)));
        assertThat(product.toString(),is(notNullValue()));
    }

    @Test
    void wheProductsAjustStock() {
        Integer out = 10;
        Integer expect = 90;
        product = new Products("101","Mouse",100,14.40);

        product.adjustStock(out);

        assertThat(product.getStock(),is(equalTo(expect)));
    }

}