package com.ajromero.webapp.web.validation.checkout;

import com.ajromero.webapp.persistence.repositories.ICustomerRepository;
import com.ajromero.webapp.persistence.repositories.IProductRepository;
import com.ajromero.webapp.web.dto.CheckoutBasicDto;
import com.ajromero.webapp.web.dto.CheckoutProductDto;
import com.ajromero.webapp.web.validation.IVerifyContent;
import org.springframework.stereotype.Component;


@Component
//@AllArgsConstructor
public class CheckoutValidator {

    private final ICheckoutChain<CheckoutBasicDto> productChain;
    private final ICheckoutChain<CheckoutBasicDto> checkoutBasicChain;
    private final ICheckoutChain<CheckoutProductDto> addProductChain;

    public CheckoutValidator(ICustomerRepository customerRepository, IProductRepository productsRepository, IVerifyContent verifyContent) {
        productChain = new ProductChain(productsRepository, verifyContent);
        checkoutBasicChain = new CheckoutBasicChain(customerRepository);
        addProductChain = new AddProductChain(productsRepository, verifyContent);
    }

    public boolean validate(CheckoutBasicDto basicDto) {

        checkoutBasicChain.nextValidate(productChain);

        return checkoutBasicChain.validate(basicDto);
    }

    public boolean validateProduct(CheckoutProductDto productDto) {
        return addProductChain.validate(productDto);
    }
}
