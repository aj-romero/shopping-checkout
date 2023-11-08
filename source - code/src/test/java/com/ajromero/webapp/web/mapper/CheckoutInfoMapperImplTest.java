package com.ajromero.webapp.web.mapper;

import com.ajromero.webapp.persistence.domain.*;
import com.ajromero.webapp.web.dto.AddressDto;
import com.ajromero.webapp.web.dto.CardPaymentDto;
import com.ajromero.webapp.web.dto.CheckoutInfoDto;
import com.ajromero.webapp.web.dto.CheckoutProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class CheckoutInfoMapperImplTest {

    private CheckoutInfoMapperImpl checkoutInfoMapperImplUnderTest;

    @BeforeEach
    void setUp() {
        checkoutInfoMapperImplUnderTest = new CheckoutInfoMapperImpl();
    }

    @Test
    void testToDto() {
        // Setup
        final Checkout checkout = new Checkout();
        checkout.setId(0L);
        checkout.setPaymentCode("paymentCode");
        checkout.setStatus(Checkout.Status.OPEN);
        checkout.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        checkout.setUpdatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final Customer customer = new Customer();
        customer.setId("id");
        customer.setFirstName("firstName");
        customer.setLastName("lastName");
        customer.setEmail("email");
        customer.setPhone("phone");
        checkout.setCustomer(customer);
        final CustomerAddress shippingAddress = new CustomerAddress();
        shippingAddress.setId(0L);
        shippingAddress.setAddressName("addressName");
        shippingAddress.setState("state");
        shippingAddress.setCity("city");
        shippingAddress.setStreet("street");
        shippingAddress.setReferencePhone("referencePhone");
        shippingAddress.setHouseNumber("houseNumber");
        shippingAddress.setReferenceName("referenceName");
        checkout.setShippingAddress(shippingAddress);
        final CardPayment cardPayment = new CardPayment();
        cardPayment.setId(0L);
        cardPayment.setCardNumber("cardNumber");
        cardPayment.setCardHoldername("cardHoldername");
        cardPayment.setExpirationDate("expirationDate");
        checkout.setCardPayment(cardPayment);
        final CheckoutProduct product = new CheckoutProduct();
        product.setId(0L);
        product.setQuantity(0);
        product.setPrice(0.0);
        final Product product1 = new Product();
        product1.setId(0L);
        product.setProduct(product1);
        checkout.setProducts(Set.of(product));

        // Run the test
        final CheckoutInfoDto result = checkoutInfoMapperImplUnderTest.toDto(checkout);

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
        final Set<CheckoutProductDto> result = checkoutInfoMapperImplUnderTest.toDtoList(entities);

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
        final CheckoutProductDto result = checkoutInfoMapperImplUnderTest.checkoutProductToCheckoutProductDto(
                checkoutProduct);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testToEntity() {
        // Setup
        final CheckoutInfoDto dto = new CheckoutInfoDto();
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
        dto.setPaymentCode("paymentCode");
        dto.setStatus(Checkout.Status.OPEN);
        dto.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        dto.setUpdatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final Customer customer = new Customer();
        customer.setId("id");
        customer.setFirstName("firstName");
        customer.setLastName("lastName");
        customer.setEmail("email");
        customer.setPhone("phone");
        dto.setCustomer(customer);
        final CardPaymentDto cardPayment = new CardPaymentDto();
        cardPayment.setId(0L);
        cardPayment.setCardNumber("cardNumber");
        cardPayment.setCardHoldername("cardHoldername");
        cardPayment.setExpirationDate("expirationDate");
        dto.setCardPayment(cardPayment);

        // Run the test
        final Checkout result = checkoutInfoMapperImplUnderTest.toEntity(dto);

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
        final Set<CheckoutProduct> result = checkoutInfoMapperImplUnderTest.toEntityList(dtos);

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
        final CheckoutProduct result = checkoutInfoMapperImplUnderTest.checkoutProductDtoToCheckoutProduct(
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
        final AddressDto result = checkoutInfoMapperImplUnderTest.customerAddressToAddressDto(customerAddress);

        // Verify the results
    }

    @Test
    void testCardPaymentToCardPaymentDto() {
        // Setup
        final CardPayment cardPayment = new CardPayment();
        cardPayment.setId(0L);
        final Customer customer = new Customer();
        cardPayment.setCustomer(customer);
        cardPayment.setCardNumber("cardNumber");
        cardPayment.setCardHoldername("cardHoldername");
        cardPayment.setExpirationDate("expirationDate");

        // Run the test
        final CardPaymentDto result = checkoutInfoMapperImplUnderTest.cardPaymentToCardPaymentDto(cardPayment);

        // Verify the results
    }

    @Test
    void testCustomerToCustomer() {
        // Setup
        final Customer customer = new Customer("id", "firstName", "lastName", "email", "phone");
        final Customer expectedResult = new Customer("id", "firstName", "lastName", "email", "phone");

        // Run the test
        final Customer result = checkoutInfoMapperImplUnderTest.customerToCustomer(customer);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
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
        final CustomerAddress result = checkoutInfoMapperImplUnderTest.addressDtoToCustomerAddress(addressDto);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }



}
