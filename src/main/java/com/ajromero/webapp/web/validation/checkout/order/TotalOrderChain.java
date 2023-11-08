package com.ajromero.webapp.web.validation.checkout.order;

import com.ajromero.webapp.persistence.domain.Checkout;
import com.ajromero.webapp.web.validation.IVerifyContent;
import com.ajromero.webapp.web.validation.checkout.ICheckoutChain;

public class TotalOrderChain implements ICheckoutChain<Checkout> {

    private ICheckoutChain<Checkout> validator;
    private IVerifyContent verifyContent;

    public TotalOrderChain(IVerifyContent verifyContent) {
        this.verifyContent = verifyContent;
    }

    @Override
    public Boolean validate(Checkout resource) {
        boolean result = true;
        Double total = resource.getTotalCheckout();
        if (total <= 0) {
            this.verifyContent.verifyBadRequest(true,
                    "Checkout is not valid, total is wrog");
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
