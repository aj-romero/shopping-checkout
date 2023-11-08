package com.ajromero.webapp.payment;

import com.ajromero.webapp.payment.methods.CardApi;
import com.ajromero.webapp.persistence.domain.Checkout;
import com.ajromero.webapp.web.dto.CCheckoutDto;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
public class CardProcessor implements IPaymentProcessor, Serializable {

    private transient CardApi cardApi;

    public CardProcessor(CCheckoutDto card) {
        cardApi = new CardApi(card);
    }

    @Override
    public String pay(Checkout checkout) {
        return cardApi.getSecurityCode(checkout.getTotalCheckout());
    }
}
