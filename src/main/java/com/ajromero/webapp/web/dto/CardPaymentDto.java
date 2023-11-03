package com.ajromero.webapp.web.dto;

import com.ajromero.common.interfaces.IDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CardPaymentDto implements IDto {

    private Long id;
    private String cardNumber;
    private String cardHoldername;
    private String expirationDate;

    public String getCardNumber() {
        return "**"+cardNumber.substring(this.cardNumber.length()-4);
    }

}
