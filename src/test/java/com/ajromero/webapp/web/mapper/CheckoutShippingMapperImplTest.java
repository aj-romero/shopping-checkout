package com.ajromero.webapp.web.mapper;

import com.ajromero.webapp.persistence.domain.Checkout;
import com.ajromero.webapp.persistence.domain.CheckoutProduct;
import com.ajromero.webapp.persistence.domain.CustomerAddress;
import com.ajromero.webapp.persistence.domain.Product;
import com.ajromero.webapp.web.dto.AddressDto;
import com.ajromero.webapp.web.dto.CheckoutProductDto;
import com.ajromero.webapp.web.dto.CheckoutWithShippingDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class CheckoutShippingMapperImplTest {

    private CheckoutShippingMapperImpl checkoutShippingMapperImplUnderTest;

    @BeforeEach
    void setUp() {
        checkoutShippingMapperImplUnderTest = new CheckoutShippingMapperImpl();
    }

    @Test
    void testToDto() {
        // Setup
        final Checkout entity = new Checkout();
        entity.setId(0L);
        final CustomerAddress shippingAddress = new CustomerAddress();
        shippingAddress.setId(0L);
        shippingAddress.setAddressName("addressName");
        shippingAddress.setState("state");
        shippingAddress.setCity("city");
        shippingAddress.setStreet("street");
        shippingAddress.setReferencePhone("referencePhone");
        shippingAddress.setHouseNumber("houseNumber");
        shippingAddress.setReferenceName("referenceName");
        entity.setShippingAddress(shippingAddress);
        final CheckoutProduct product = new CheckoutProduct();
        product.setId(0L);
        product.setQuantity(0);
        product.setPrice(0.0);
        final Product product1 = new Product();
        product1.setId(0L);
        product.setProduct(product1);
        entity.setProducts(Set.of(product));

        // Run the test
        final CheckoutWithShippingDto result = checkoutShippingMapperImplUnderTest.toDto(entity);

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
        final Set<CheckoutProductDto> result = checkoutShippingMapperImplUnderTest.toDtoList(entities);

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
        final CheckoutProductDto result = checkoutShippingMapperImplUnderTest.checkoutProductToCheckoutProductDto(
                checkoutProduct);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testToEntity() {
        // Setup
        final CheckoutWithShippingDto dto = new CheckoutWithShippingDto();
        dto.setId(0L);
        final CheckoutProductDto checkoutProductDto = new CheckoutProductDto();
        checkoutProductDto.setId(0L);
        checkoutProductDto.setIdProduct(0L);
        checkoutProductDto.setQuantity(0);
        checkoutProductDto.setPrice(0.0);
        dto.setProducts(Set.of(checkoutProductDto));
        final AddressDto shippingAddress = new AddressDto();
        shippingAddress.setId(0L);
        shippingAddress.setAddressName("addressName");
        shippingAddress.setState("state");
        shippingAddress.setCity("city");
        shippingAddress.setStreet("street");
        shippingAddress.setReferencePhone("referencePhone");
        shippingAddress.setHouseNumber("houseNumber");
        shippingAddress.setReferenceName("referenceName");
        dto.setShippingAddress(shippingAddress);

        // Run the test
        final Checkout result = checkoutShippingMapperImplUnderTest.toEntity(dto);

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
        final Set<CheckoutProduct> result = checkoutShippingMapperImplUnderTest.toEntityList(dtos);

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
        final CheckoutProduct result = checkoutShippingMapperImplUnderTest.checkoutProductDtoToCheckoutProduct(
                checkoutProductDto);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testCustomerAddressToAddressDto() {
        // Setup
        final CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setId(0L);
        customerAddress.setAddressName("addressName");
        customerAddress.setState("state");
        customerAddress.setCity("city");
        customerAddress.setStreet("street");
        customerAddress.setReferencePhone("referencePhone");
        customerAddress.setHouseNumber("houseNumber");
        customerAddress.setReferenceName("referenceName");

        // Run the test
        final AddressDto result = checkoutShippingMapperImplUnderTest.customerAddressToAddressDto(customerAddress);

        // Verify the results
    }

    @Test
    void testAddressDtoToCustomerAddress() {
        // Setup
        final AddressDto addressDto = new AddressDto();
        addressDto.setId(0L);
        addressDto.setAddressName("addressName");
        addressDto.setState("state");
        addressDto.setCity("city");
        addressDto.setStreet("street");
        addressDto.setReferencePhone("referencePhone");
        addressDto.setHouseNumber("houseNumber");
        addressDto.setReferenceName("referenceName");

        final CustomerAddress expectedResult = new CustomerAddress();
        expectedResult.setId(0L);
        expectedResult.setAddressName("addressName");
        expectedResult.setState("state");
        expectedResult.setCity("city");
        expectedResult.setStreet("street");
        expectedResult.setReferencePhone("referencePhone");
        expectedResult.setHouseNumber("houseNumber");
        expectedResult.setReferenceName("referenceName");

        // Run the test
        final CustomerAddress result = checkoutShippingMapperImplUnderTest.addressDtoToCustomerAddress(addressDto);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }


}
