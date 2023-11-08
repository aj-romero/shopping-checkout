package com.ajromero.webapp.web.validation.checkout.order;

import com.ajromero.webapp.persistence.domain.CardPayment;
import com.ajromero.webapp.persistence.domain.Checkout;
import com.ajromero.webapp.persistence.domain.Customer;
import com.ajromero.webapp.web.validation.IVerifyContent;
import com.ajromero.webapp.web.validation.checkout.ICheckoutChain;

public class PaymentChain implements ICheckoutChain<Checkout> {

    private ICheckoutChain<Checkout> validator;
    private IVerifyContent verifyContent;

    public PaymentChain(IVerifyContent verifyContent) {
        this.verifyContent = verifyContent;
    }

    @Override
    public Boolean validate(Checkout resource) {
        boolean result = true;
        CardPayment card = resource.getCardPayment();
        if (card == null) {
            this.verifyContent.verifyBadRequest(true,
                    "Payment method was not added");
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
