package com.ajromero.webapp.payment.methods;

import com.ajromero.common.interfaces.IDto;
import com.ajromero.webapp.payment.api.GenerateUuid;
import com.ajromero.webapp.web.dto.CCheckoutDto;

public class CardApi implements IDto {

    private static final long serialVersionUID = 1905122041950251207L;

    private final CCheckoutDto card;

    public CardApi(final CCheckoutDto card) {
        this.card = card;
    }

    public String getSecurityCode(Double amount) {
        GenerateUuid uuid = GenerateUuid.getInstance();
        return "**" + card.getCardNumber()
                .substring(card.getCardNumber().length() - 4)
                + " CP - " + uuid.generateCode() + " - " + amount + " USD$";
    }
}
