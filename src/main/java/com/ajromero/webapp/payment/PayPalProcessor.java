package com.ajromero.webapp.payment;

import com.ajromero.common.interfaces.IDto;
import com.ajromero.webapp.payment.methods.PayPal;
import com.ajromero.webapp.persistence.domain.Checkout;

public class PayPalProcessor implements IPaymentProcessor{

    private PayPal payPal;

    public  PayPalProcessor(String email) {
        payPal = new PayPal(email);
    }

    @Override
    public String pay(Checkout checkout) {
        //checkout.setPaymentMethod(IPaymentMethod );
        return payPal.getSecurityCode(checkout.getTotalCheckout());
    }
}
