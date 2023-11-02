package com.ajromero.webapp.payment.methods;

import com.ajromero.webapp.payment.api.GenerateUuid;
import lombok.Getter;
import lombok.Setter;

public class PayPal {

    @Getter
    @Setter
    private String email;

    public PayPal(String email) {
        this.email = email;
    }

    public String getSecurityCode(Double amount) {
        GenerateUuid uuid = GenerateUuid.getInstance();
        String result = this.email + " PP - " + uuid.generateCode() +" - "+ amount + " USD$";
        return result;
    }
}
