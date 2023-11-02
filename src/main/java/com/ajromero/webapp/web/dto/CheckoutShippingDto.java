package com.ajromero.webapp.web.dto;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

public class CheckoutShippingDto extends CheckoutBasicDto{

    @Setter @Getter
    private AddressDto shippingAddress;

    public CheckoutShippingDto() {
    }
}
