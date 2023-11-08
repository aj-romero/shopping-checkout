package com.ajromero.webapp.web.mapper;

import com.ajromero.webapp.persistence.domain.CheckoutProduct;
import com.ajromero.webapp.persistence.domain.Product;
import com.ajromero.webapp.web.dto.CheckoutProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CheckoutProductMapperImplTest {

    private CheckoutProductMapperImpl checkoutProductMapperImplUnderTest;

    @BeforeEach
    void setUp() {
        checkoutProductMapperImplUnderTest = new CheckoutProductMapperImpl();
    }

    @Test
    void testToDto() {
        // Setup
        final CheckoutProduct entity = new CheckoutProduct();
        entity.setId(0L);
        entity.setQuantity(0);
        entity.setPrice(0.0);
        final Product product = new Product();
        product.setId(0L);
        entity.setProduct(product);

        final CheckoutProductDto expectedResult = new CheckoutProductDto();
        expectedResult.setId(0L);
        expectedResult.setIdProduct(0L);
        expectedResult.setQuantity(0);
        expectedResult.setPrice(0.0);

        // Run the test
        final CheckoutProductDto result = checkoutProductMapperImplUnderTest.toDto(entity);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testToEntity() {
        // Setup
        final CheckoutProductDto dto = new CheckoutProductDto();
        dto.setId(0L);
        dto.setIdProduct(0L);
        dto.setQuantity(0);
        dto.setPrice(0.0);

        final CheckoutProduct expectedResult = new CheckoutProduct();
        expectedResult.setId(0L);
        expectedResult.setQuantity(0);
        expectedResult.setPrice(0.0);
        final Product product = new Product();
        product.setId(0L);
        expectedResult.setProduct(product);

        // Run the test
        final CheckoutProduct result = checkoutProductMapperImplUnderTest.toEntity(dto);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }


}
