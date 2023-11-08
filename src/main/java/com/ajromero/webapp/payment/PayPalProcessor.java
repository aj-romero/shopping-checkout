package com.ajromero.webapp.payment;

import com.ajromero.webapp.payment.methods.PayPal;
import com.ajromero.webapp.persistence.domain.Checkout;

import java.io.Serializable;

public class PayPalProcessor implements IPaymentProcessor, Serializable {

    private transient PayPal payPal;

    public  PayPalProcessor(String email) {
        payPal = new PayPal(email);
    }

    @Override
    public String pay(Checkout checkout) {
        return payPal.getSecurityCode(checkout.getTotalCheckout());
    }
}
