package com.ajromero.webapp.payment;

import com.ajromero.common.interfaces.IDto;
import com.ajromero.webapp.persistence.domain.Checkout;

public interface IPaymentProcessor {


    String pay(Checkout checkout);
}
