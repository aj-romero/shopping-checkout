package com.ajromero.webapp.web.dto;

import com.ajromero.webapp.persistence.domain.Checkout;
import com.ajromero.webapp.persistence.domain.Customer;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CheckoutInfoDto extends CheckoutWithShippingDto {
    private String paymentCode;
    private Checkout.Status status;
    private Date createdAt;
    private Date updatedAt;
    private Customer customer;
    private CardPaymentDto cardPayment;
}
