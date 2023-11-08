package com.ajromero.webapp.web.mapper;

import com.ajromero.webapp.persistence.domain.Checkout;
import com.ajromero.webapp.persistence.domain.CheckoutProduct;
import com.ajromero.webapp.persistence.domain.Product;
import com.ajromero.webapp.web.dto.CheckoutBasicDto;
import com.ajromero.webapp.web.dto.CheckoutProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class CheckoutBasicMapperImplTest {

    private CheckoutBasicMapperImpl checkoutBasicMapperImplUnderTest;

    @BeforeEach
    void setUp() {
        checkoutBasicMapperImplUnderTest = new CheckoutBasicMapperImpl();
    }

    @Test
    void testToDto() {
        // Setup
        final Checkout entity = new Checkout();
        entity.setId(0L);
        final CheckoutProduct product = new CheckoutProduct();
        product.setId(0L);
        product.setQuantity(0);
        product.setPrice(0.0);
        final Product product1 = new Product();
        product1.setId(0L);
        product.setProduct(product1);
        entity.setProducts(Set.of(product));

        // Run the test
        final CheckoutBasicDto result = checkoutBasicMapperImplUnderTest.toDto(entity);

        // Verify the results
    }

    @Test
    void testToDtoList() {
        // Setup
        final CheckoutProduct product = new CheckoutProduct();
        product.setId(0L);
        product.setQuantity(0);
        product.setPrice(0.0);
        final Product product1 = new Product();
        product1.setId(0L);
        product.setProduct(product1);
        final Set<CheckoutProduct> entities = Set.of(product);
        final CheckoutProductDto checkoutProductDto = new CheckoutProductDto();
        checkoutProductDto.setId(0L);
        checkoutProductDto.setIdProduct(0L);
        checkoutProductDto.setQuantity(0);
        checkoutProductDto.setPrice(0.0);
        final Set<CheckoutProductDto> expectedResult = Set.of(checkoutProductDto);

        // Run the test
        final Set<CheckoutProductDto> result = checkoutBasicMapperImplUnderTest.toDtoList(entities);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testCheckoutProductToCheckoutProductDto() {
        // Setup
        final CheckoutProduct checkoutProduct = new CheckoutProduct();
        checkoutProduct.setId(0L);
        checkoutProduct.setQuantity(0);
        checkoutProduct.setPrice(0.0);
        final Product product = new Product();
        product.setId(0L);
        checkoutProduct.setProduct(product);

        final CheckoutProductDto expectedResult = new CheckoutProductDto();
        expectedResult.setId(0L);
        expectedResult.setIdProduct(0L);
        expectedResult.setQuantity(0);
        expectedResult.setPrice(0.0);

        // Run the test
        final CheckoutProductDto result = checkoutBasicMapperImplUnderTest.checkoutProductToCheckoutProductDto(
                checkoutProduct);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testToEntity() {
        // Setup
        final CheckoutProductDto checkoutProductDto = new CheckoutProductDto();
        checkoutProductDto.setId(0L);
        checkoutProductDto.setIdProduct(0L);
        checkoutProductDto.setQuantity(0);
        checkoutProductDto.setPrice(0.0);
        final CheckoutBasicDto dto = new CheckoutBasicDto(Set.of(checkoutProductDto));

        // Run the test
        final Checkout result = checkoutBasicMapperImplUnderTest.toEntity(dto);

        // Verify the results
    }

    @Test
    void testToEntityList() {
        // Setup
        final CheckoutProductDto checkoutProductDto = new CheckoutProductDto();
        checkoutProductDto.setId(0L);
        checkoutProductDto.setIdProduct(0L);
        checkoutProductDto.setQuantity(0);
        checkoutProductDto.setPrice(0.0);
        final Set<CheckoutProductDto> dtos = Set.of(checkoutProductDto);
        final CheckoutProduct product = new CheckoutProduct();
        product.setId(0L);
        product.setQuantity(0);
        product.setPrice(0.0);
        final Product product1 = new Product();
        product1.setId(0L);
        product.setProduct(product1);
        final Set<CheckoutProduct> expectedResult = Set.of(product);

        // Run the test
        final Set<CheckoutProduct> result = checkoutBasicMapperImplUnderTest.toEntityList(dtos);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testCheckoutProductDtoToCheckoutProduct() {
        // Setup
        final CheckoutProductDto checkoutProductDto = new CheckoutProductDto();
        checkoutProductDto.setId(0L);
        checkoutProductDto.setIdProduct(0L);
        checkoutProductDto.setQuantity(0);
        checkoutProductDto.setPrice(0.0);

        final CheckoutProduct expectedResult = new CheckoutProduct();
        expectedResult.setId(0L);
        expectedResult.setQuantity(0);
        expectedResult.setPrice(0.0);
        final Product product = new Product();
        product.setId(0L);
        expectedResult.setProduct(product);

        // Run the test
        final CheckoutProduct result = checkoutBasicMapperImplUnderTest.checkoutProductDtoToCheckoutProduct(
                checkoutProductDto);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }


}
