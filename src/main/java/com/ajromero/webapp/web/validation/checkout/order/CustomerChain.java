package com.ajromero.webapp.web.validation.checkout.order;

import com.ajromero.webapp.persistence.domain.Checkout;
import com.ajromero.webapp.persistence.domain.Customer;
import com.ajromero.webapp.web.validation.IVerifyContent;
import com.ajromero.webapp.web.validation.checkout.ICheckoutChain;

public class CustomerChain implements ICheckoutChain<Checkout> {

    private ICheckoutChain<Checkout> validator;
    private IVerifyContent verifyContent;

    public CustomerChain(IVerifyContent verifyContent) {
        this.verifyContent = verifyContent;
    }

    @Override
    public Boolean validate(Checkout resource) {
        boolean result = true;
        Customer customer = resource.getCustomer();
        if (customer.getEmail().isEmpty()) {
            this.verifyContent.verifyBadRequest(true,
                    "Customer email is required");
            result = false;
        }
        if (customer.getFirstName().isEmpty()) {
            this.verifyContent.verifyBadRequest(true,
                    "Customer first name is required");
            result = false;
        }
        if (customer.getLastName().isEmpty()) {
            this.verifyContent.verifyBadRequest(true,
                    "Customer last name is required");
            result = false;
        }
        if (validator != null) {
            return this.validator.validate(resource);
        }
        return result;
    }

    @Override
    public void nextValidate(ICheckoutChain<Checkout> validator) {
        this.validator = validator;
    }
}
