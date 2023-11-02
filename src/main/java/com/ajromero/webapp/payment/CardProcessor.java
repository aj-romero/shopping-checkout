package com.ajromero.webapp.payment;

import com.ajromero.webapp.payment.IPaymentProcessor;
import com.ajromero.webapp.payment.api.GenerateUuid;
import com.ajromero.webapp.payment.methods.CardApi;
import com.ajromero.webapp.persistence.domain.Checkout;
import com.ajromero.webapp.web.dto.CCheckoutDto;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CardProcessor implements IPaymentProcessor {

    private CardApi cardApi;

    public CardProcessor(CCheckoutDto card) {
        cardApi = new CardApi(card);
    }
    @Override
    public String pay(Checkout checkout) {
       return cardApi.getSecurityCode(checkout.getTotalCheckout());
    }
}
