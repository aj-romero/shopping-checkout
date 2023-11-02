package com.ajromero.webapp.payment.methods;

import com.ajromero.webapp.payment.api.GenerateUuid;
import com.ajromero.webapp.web.dto.CCheckoutDto;

public class CardApi {

    private final CCheckoutDto card;

    public CardApi(CCheckoutDto card) {
        this.card = card;
    }

    public String getSecurityCode(Double amount) {
        GenerateUuid uuid = GenerateUuid.getInstance();
        return "**"+ card.getCreditCardNumber().substring(card.getCreditCardNumber().length()-4)+
                " CP - " + uuid.generateCode() +" - "+ amount + " USD$";
    }
}
