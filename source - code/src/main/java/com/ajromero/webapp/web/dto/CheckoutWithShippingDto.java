package com.ajromero.webapp.web.dto;

import lombok.Getter;
import lombok.Setter;

public class CheckoutWithShippingDto extends CheckoutBasicDto {

    @Setter @Getter
    private AddressDto shippingAddress;

    public CheckoutWithShippingDto() {
        //
    }
}
