package com.ajromero.webapp.web.validation.checkout.order;

import com.ajromero.webapp.persistence.domain.Checkout;
import com.ajromero.webapp.persistence.domain.CustomerAddress;
import com.ajromero.webapp.web.validation.IVerifyContent;
import com.ajromero.webapp.web.validation.checkout.ICheckoutChain;

public class AddressChain implements ICheckoutChain<Checkout> {

    private ICheckoutChain<Checkout> validator;
    private IVerifyContent verifyContent;

    public AddressChain(IVerifyContent verifyContent) {
        this.verifyContent = verifyContent;
    }
    @Override
    public Boolean validate(Checkout resource) {
        boolean result = true;
        CustomerAddress address = resource.getShippingAddress();
        if ( address == null ) {
            this.verifyContent.verifyBadRequest(true,
                    "Shipping Address was not added");
            result = false;
        }

        if( validator != null) {
            return this.validator.validate(resource);
        }

        return result;
    }

    @Override
    public void nextValidate(ICheckoutChain<Checkout> validator) {
            this.validator = validator;
    }
}
