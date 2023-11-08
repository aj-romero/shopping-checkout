package com.ajromero.webapp.web.validation.checkout;

import com.ajromero.webapp.persistence.domain.Checkout;
import com.ajromero.webapp.persistence.repositories.ICustomerRepository;
import com.ajromero.webapp.persistence.repositories.IProductRepository;
import com.ajromero.webapp.web.dto.CheckoutBasicDto;
import com.ajromero.webapp.web.dto.CheckoutProductDto;
import com.ajromero.webapp.web.validation.IVerifyContent;
import com.ajromero.webapp.web.validation.checkout.order.AddressChain;
import com.ajromero.webapp.web.validation.checkout.order.CustomerChain;
import com.ajromero.webapp.web.validation.checkout.order.PaymentChain;
import com.ajromero.webapp.web.validation.checkout.order.TotalOrderChain;
import org.springframework.stereotype.Component;


@Component
//@AllArgsConstructor
public class CheckoutValidator {

    private final ICheckoutChain<CheckoutBasicDto> productChain;
    private final ICheckoutChain<CheckoutBasicDto> checkoutBasicChain;
    private final ICheckoutChain<CheckoutProductDto> addProductChain;
    private final ICheckoutChain<Checkout> addressChain;
    private final ICheckoutChain<Checkout> customerChain;
    private final ICheckoutChain<Checkout> paymentChain;
    private final ICheckoutChain<Checkout> totalOrderChain;

    public CheckoutValidator(ICustomerRepository customerRepository,
                             IProductRepository productsRepository,
                             IVerifyContent verifyContent) {
        productChain = new ProductChain(productsRepository, verifyContent);
        checkoutBasicChain = new CheckoutBasicChain(customerRepository);
        addProductChain = new AddProductChain(productsRepository, verifyContent);
        addressChain = new AddressChain(verifyContent);
        customerChain = new CustomerChain(verifyContent);
        paymentChain = new PaymentChain(verifyContent);
        totalOrderChain = new TotalOrderChain(verifyContent);

    }

    public boolean validate(CheckoutBasicDto basicDto) {

        checkoutBasicChain.nextValidate(productChain);

        return checkoutBasicChain.validate(basicDto);
    }

    public boolean validateProduct(CheckoutProductDto productDto) {
        return addProductChain.validate(productDto);
    }

    public boolean validateOrder(Checkout ck) {
        totalOrderChain.nextValidate(addressChain);
        addressChain.nextValidate(customerChain);
        customerChain.nextValidate(paymentChain);
        return totalOrderChain.validate(ck);
    }
}
