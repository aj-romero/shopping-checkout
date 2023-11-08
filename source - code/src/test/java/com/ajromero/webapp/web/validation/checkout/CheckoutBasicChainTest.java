package com.ajromero.webapp.web.validation.checkout;

import com.ajromero.webapp.persistence.domain.Customer;
import com.ajromero.webapp.persistence.repositories.ICustomerRepository;
import com.ajromero.webapp.web.dto.CheckoutBasicDto;
import com.ajromero.webapp.web.dto.CheckoutProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class CheckoutBasicChainTest {

    @Mock
    private ICustomerRepository mockCustomers;
    @Mock
    private ICheckoutChain<CheckoutBasicDto> mockValidator;

    @Mock
    SecurityContext securityContext;

    @InjectMocks
    private CheckoutBasicChain checkoutBasicChainUnderTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testValidate() {
        // Setup
        final CheckoutProductDto productDto = new CheckoutProductDto();
        productDto.setId(0L);
        productDto.setIdProduct(0L);
        productDto.setQuantity(0);
        productDto.setPrice(0.0);
        final CheckoutBasicDto resource = new CheckoutBasicDto(Set.of(productDto));
        String idUser = getUserAuth();
        when(mockCustomers.existsById(anyString())).thenReturn(false);
        when(mockValidator.validate(any(CheckoutBasicDto.class))).thenReturn(false);

        // Run the test
        final Boolean result = checkoutBasicChainUnderTest.validate(resource);

        // Verify the results
        assertThat(result).isTrue();
        //verify(mockCustomers).save(new Customer("id", "firstName", "lastName", "email", "phone"));
    }

    /*@Test
    void testValidate_ICustomerRepositoryExistsByIdReturnsTrue() {
        // Setup
        final CheckoutProductDto productDto = new CheckoutProductDto();
        productDto.setId(0L);
        productDto.setIdProduct(0L);
        productDto.setQuantity(0);
        productDto.setPrice(0.0);
        final CheckoutBasicDto resource = new CheckoutBasicDto(Set.of(productDto));
        when(mockCustomers.existsById("id")).thenReturn(true);
        when(mockValidator.validate(any(CheckoutBasicDto.class))).thenReturn(false);

        // Run the test
        final Boolean result = checkoutBasicChainUnderTest.validate(resource);

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testValidate_ICheckoutChainReturnsTrue() {
        // Setup
        final CheckoutProductDto productDto = new CheckoutProductDto();
        productDto.setId(0L);
        productDto.setIdProduct(0L);
        productDto.setQuantity(0);
        productDto.setPrice(0.0);
        final CheckoutBasicDto resource = new CheckoutBasicDto(Set.of(productDto));
        when(mockCustomers.existsById("id")).thenReturn(false);
        when(mockValidator.validate(any(CheckoutBasicDto.class))).thenReturn(true);

        // Run the test
        final Boolean result = checkoutBasicChainUnderTest.validate(resource);

        // Verify the results
        assertThat(result).isTrue();
        verify(mockCustomers).save(new Customer("id", "firstName", "lastName", "email", "phone"));
    }*/

    String getUserAuth() {
        String idUser = "";
        Authentication authMock = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authMock);
        SecurityContextHolder.setContext(securityContext);
        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn(idUser);
        return idUser;
    }
}
